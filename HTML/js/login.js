function validateLogin(username, password, user) {
    // 判断账号类型
    if (/^\d+$/.test(username)) {
        // id
        user.id = parseInt(username);
        user.name = null;
        user.email = null;
    }
    else if (/^[A-Za-z0-9_]+$/.test(username) && username.indexOf('@') === -1) {
        // username
        user.id = null;
        user.name = username;
        user.email = null;
    }
    else if (/^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/.test(username)) {
        // email
        user.id = null;
        user.name = null;
        user.email = username;
    }
    else {
        alert('账号格式错误');
        return false;
    }

    // 检查密码
    if (password === '') {
        alert('密码不能为空');
        return false;
    }
    user.passwd = password;

    return true;
}

$(document).ready(function (){
    let user = {
        "id" : 0,
        "name" : null,
        "email" : null,
        "passwd" : null
    }
    var url;

    fetch("../config.json").then(data => {
        url = data.url;
    });

    $('#login').click(function (event){
        event.preventDefault();

        var username = $('#username').val();
        var password = $('#password').val();
        if (validateLogin(username, password, user)) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "http://127.0.0.1:8080/DiscordLike/user/login",
                contentType: "application/json",
                data: JSON.stringify(user),
                success: function (result){
                    alert("登录成功!");
                },
                error: function (jqXHR){
                    var code = jqXHR.status;
                    if(code == 401){
                        alert("密码错误!");
                    }
                    else if(code == 404){
                        alert("用户不存在!");
                    }
                    else{
                        alert("网络连接错误！");
                    }
                }
            });
        }
    });
});
