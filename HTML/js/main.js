// 检查登录
function login(){
    var isLoggedIn = sessionStorage.getItem("login");
    console.log(isLoggedIn);

    if (!isLoggedIn) {
        // 未登录状态
        $('#profile-btn').text("未登录");
        $('#profile-btn').prop("disabled", true);
        $('#create-server').prop("disabled", true);
    } else {
        // 登录状态，展示个人信息
        $('#profile-btn').text("个人信息");
        $('#profile-btn').prop("disabled", false);
        $('#create-server').prop("disabled", false);
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

// 刷新服务器列表
function refreshServerList(){
    var id = JSON.parse(sessionStorage.getItem("user")).id;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "http://127.0.0.1:8080/DiscordLike/server/getList?id=" + id.toString(),
        contentType: "application/json",
        success: function (result){
            $('.server').remove();
            let k = 1;
            for(let server of result){
                let newServer = $('<button class=\"server\" data-server=\"' + server.id.toString() + '\">' + server.name + '</button>');
                $('#serverList').append(newServer);
                k++;
            }
            $('.server').click(function (){
                console.log($(this).data("server"));
                $('#show-page').attr("src", "server.html?id=" + $(this).data("server"));
            });
        }
    })
}

$(document).ready(function (){
    // 检查登录
    login();

    $('#create-server-dialog').hide();

    // 个人信息
    $('#profile-btn').click(function (){
        $('#show-page').attr("src", "user.html");
    });

    // 添加服务器
    $('#create-server').click(function (){
        $('#create-server-dialog').show();
    });
    $('#create-server-createBtn').click(function (){
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
            url: "http://127.0.0.1:8080/DiscordLike/server/create",
            contentType: "application/json",
            data: JSON.stringify(server),
            success: function (result){
                alert('服务器创建成功');
                // 刷新页面
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
});