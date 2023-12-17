function validateLogin(account, password, user) {
    if(account === ''){
        alert('账号不能为空');
        return false;
    }

    // 判断账号类型
    if (/^\d+$/.test(account)) {
        // id
        user.id = parseInt(account);
        user.name = null;
        user.email = null;
    }
    else if (/^[A-Za-z0-9_]+$/.test(account) && account.indexOf('@') === -1) {
        // username
        user.id = null;
        user.name = account;
        user.email = null;
    }
    else if (/^\w+@[a-zA-Z0-9_]+?\.[a-zA-Z]{2,3}$/.test(account)) {
        // email
        user.id = null;
        user.name = null;
        user.email = account;
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

    $('#login').click(function (event){
        event.preventDefault();

        var account = $('#account').val();
        var password = $('#password').val();

        if (validateLogin(account, password, user)) {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/DiscordLike/user/login",
                contentType: "application/json",
                data: JSON.stringify(user),
                success: function (result){
                    console.log(JSON.stringify(result));
                    sessionStorage.setItem("login", true);
                    sessionStorage.setItem("user", JSON.stringify(result));
                    alert("登录成功!");
                    // 检查是否在iframe中
                    if (window.self !== window.top) {
                        parent.$('#show-page').attr("src", "../html/user.html");
                        parent.login();
                        parent.refreshServerList();
                    } else {
                        window.location.href = '../html/main.html';
                    }
                },
                error: function (jqXHR){
                    var code = jqXHR.status;
                    if(code === 401){
                        alert("密码错误!");
                    }
                    else if(code === 404){
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
