function refreshPage(userId, serverId){
    // 获取服务器详情
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "http://127.0.0.1:8080/DiscordLike/server/getInfo?userId=" + userId.toString() + "&serverId=" + serverId.toString(),
        contentType: "application/json",
        success: function (result){
            // 侧边栏
            $('.channel').remove();
            // 文本频道
            for(let channel of result.textChannels){
                let newChannel = $('<button class=\"channel\" data-textChannel=\"' + channel.id.toString() + '\">' + channel.name + '</button>');
                $('#text-channel-list').append(newChannel);
            }
            // 语音频道
            for(let channel of result.audioChannels){
                let newChannel = $('<button class=\"channel\" data-audioChannel=\"' + channel.id.toString() + '\">' + channel.name + '</button>');
                $('#audio-channel-list').append(newChannel);
            }

            sessionStorage.setItem("server", JSON.stringify(result));
        }
    });

    // 信息页
    $('#info-btn').click();
}

// 检查创建频道的表单
function validateChannel(name, desc){
    if (name === '') {
        alert('频道名称不能为空');
        return false;
    }
    var nameRegex = /^[A-Za-z0-9_]{4,16}$/;
    if (!nameRegex.test(name)) {
        alert('频道名称格式错误');
        return false;
    }

    if(desc === ''){
        alert('频道简介不能为空');
        return false;
    }
    if(desc.length > 100){
        alert('频道简介过长');
        return false;
    }

    return true;
}

$(document).ready(function (){
    $('#create-channel-dialog').hide();

    let userId = JSON.parse(sessionStorage.getItem("user")).id;
    let serverId = new URLSearchParams(window.location.search).get('id');

    refreshPage(userId, serverId);

    // 服务器信息
    $('#info-btn').click(function (){
        $('#show-page').attr('src', '../html/server-info.html');
    });

    // 添加频道
    $('#create-channel').click(function (){
        $('#create-channel-dialog').show();
    });
    $('#create-channel-createBtn').click(function (event){
        event.preventDefault();

        // 获取表单内容
        var channelName = $('#input-channelName').val();
        var channelDescription = $('#input-channelDescription').val();
        var channelType = $('input[name="channel-type"]:checked').val();
        console.log(channelName);
        console.log(channelDescription);
        console.log(channelType);
        console.log(serverId);

        if(!validateChannel(channelName, channelDescription)){
            return;
        }

        // 发送创建请求
        var channel = {
            "name": channelName,
            "description": channelDescription,
            "owner": {
                "id": JSON.parse(sessionStorage.getItem("user")).id
            }
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "http://127.0.0.1:8080/DiscordLike/channel/create?type=" + channelType + "&serverId=" + serverId.toString(),
            contentType: "application/json",
            data: JSON.stringify(channel),
            success: function (result){
                alert('频道创建成功');
                $('#create-channel-cancelBtn').click();
                refreshPage(userId, serverId);
            },
            error: function (jqXHR){
                alert('服务器创建失败');
            }
        });
    })
    $('#create-channel-cancelBtn').click(function (){
        $('#create-channel-dialog').hide();
        // 清除表单内容
        $('#input-channelName').val('');
        $('#input-channelDescription').val('');
    });

    // 删除
    $('#delete-server').click(function (){
        var serverOwnerId = JSON.parse(sessionStorage.getItem("server")).owner.id;
        var conf;
        if(serverOwnerId === userId){
            conf = confirm("您是服务器的创建者，删除后所有相关数据都将一并删除，确认删除吗");
        }
        else{
            conf = confirm("您是服务器的加入者，删除后该服务器将从您的服务器列表移除，确认删除吗");
        }

        if(conf) {
            var sid = prompt("请输入该服务器id以确认");
            if(sid === serverId){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "http://127.0.0.1:8080/DiscordLike/server/delete?serverId=" + serverId.toString(),
                    contentType: "application/json",
                    data: JSON.stringify({"id": userId}),
                    success: function (result){
                        alert("删除成功");
                        sessionStorage.removeItem("server");
                        parent.location.reload();
                    },
                    error: function (jqXHR){
                        var code = jqXHR.status;
                        if(code === 400){
                            alert("删除失败");
                        }
                    }
                })
            }
            else{
                alert("输入错误");
            }
        }
    });

    // 返回
    $('#back-btn').click(function (){
        window.location.href = '../html/user.html';
    })
});