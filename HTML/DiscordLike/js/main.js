// 检查登录
function login(){
    var isLoggedIn = sessionStorage.getItem("login");
    console.log(isLoggedIn);

    if (!isLoggedIn) {
        // 未登录状态
        $('#profile-btn').text("未登录");
        $('#profile-btn').prop("disabled", true);
        $('#create-server').prop("disabled", true);
        $('#join-server').prop("disabled", true)
    } else {
        // 登录状态，展示个人信息
        $('#profile-btn').text("个人信息");
        $('#profile-btn').prop("disabled", false);
        $('#create-server').prop("disabled", false);
        $('#join-server').prop("disabled", false)
        $('#show-page').attr("src", "user.html");
        refreshServerList();
    }
}

// 检查创建服务器的表单
function validateServer(name, desc){
    if (name === '') {
        alert('服务器名称不能为空');
        return false;
    }
    var nameRegex = /^[A-Za-z0-9_]{4,16}$/;
    if (!nameRegex.test(name)) {
        alert('服务器名称格式错误');
        return false;
    }

    if(desc === ''){
        alert('服务器简介不能为空');
        return false;
    }
    if(desc.length > 100){
        alert('服务器简介过长');
        return false;
    }

    return true;
}

// 检查服务器ID格式
function validateServerId(serverId){
    if(serverId === ''){
        alert("id不能为空")
        return false;
    }

    if(/^\d+$/.test(serverId)){
        return true;
    }

    alert("ID格式错误");
    return false;
}

// 刷新服务器列表
function refreshServerList(){
    var id = JSON.parse(sessionStorage.getItem("user")).id;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/DiscordLike/server/getList?id=" + id.toString(),
        contentType: "application/json",
        success: function (result){
            // 添加服务器
            $('.server').remove();
            for(let server of result){
                console.log(JSON.stringify(server))
                let newServer = $('<button class=\"server\" data-server=\"' + server.id.toString() + '\">' + server.name + '</button>');
                $('#serverList').append(newServer);
            }
            $('.server').click(function (){
                $('#show-page').attr("src", "server.html?id=" + $(this).data("server"));
            });
        }
    })
}

$(document).ready(function (){
    // 检查登录
    login();

    $('#create-server-dialog').hide();
    $('#join-server-dialog').hide();

    // 个人信息
    $('#profile-btn').click(function (){
        $('#show-page').attr("src", "user.html");
    });

    // 添加服务器
    $('#create-server').click(function (){
        $('#create-server-dialog').show();
    });
    $('#create-server-createBtn').click(function (event){
        event.preventDefault();

        // 检查表单内容
        var serverName = $('#input-serverName').val();
        var serverDesc = $('#input-serverDescription').val();
        if(!validateServer(serverName, serverDesc)){
            return;
        }

        // 发送创建请求
        var server = {
            "name": serverName,
            "description": serverDesc,
            "owner": {
                "id": JSON.parse(sessionStorage.getItem("user")).id
            }
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/DiscordLike/server/create",
            contentType: "application/json",
            data: JSON.stringify(server),
            success: function (result){
                alert('服务器创建成功');
                $('#create-server-cancelBtn').click();
                refreshServerList();
            },
            error: function (jqXHR){
                alert('服务器创建失败');
            }
        });
    });
    $('#create-server-cancelBtn').click(function (){
       $('#create-server-dialog').hide();
       // 清除表单内容
        $('#input-serverName').val('');
        $('#input-serverDescription').val('');
    });

    // 加入服务器
    $('#join-server').click(function (){
        $('#join-server-dialog').show();
    })
    $('#join-server-joinBtn').click(function (event){
        event.preventDefault();

        var serverId = $('#input-serverId').val();
        if(!validateServerId(serverId)){
            return;
        }

        var user = {
            "id": JSON.parse(sessionStorage.getItem("user")).id
        };
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/DiscordLike/server/join?serverId=" + serverId.toString(),
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function (result){
                alert("加入成功");
                window.location.reload();
            },
            error: function (jqXHR){
                var code = jqXHR.status;
                if(code === 400){
                    alert("请求错误");
                }
                else if(code === 404){
                    alert("服务器不存在");
                }
                else if(code === 409){
                    alert("你已经在该服务器了");
                }
            }
        })
    })
    $('#join-server-cancelBtn').click(function (){
        $('#join-server-dialog').hide();
        $('#input-serverId').val('');
    })
});