// 用户名检查函数
function validateUsername(username) {
    if (username === '') {
        alert('用户名不能为空');
        return false;
    }

    var usernameRegex = /^[A-Za-z0-9_]{4,16}$/;
    if (!usernameRegex.test(username)) {
        alert('用户名格式错误');
        return false;
    }

    return true;
}

// 邮箱检查函数
function validateEmail(email) {
    if (email === '') {
        alert('邮箱不能为空');
        return false;
    }

    var emailRegex = /\S+@\S+\.\S+/;
    if (!emailRegex.test(email)) {
        alert('邮箱格式不正确');
        return false;
    }

    return true;
}

// 密码检查函数
function validatePassword(password, confirmPassword) {
    if (password === '') {
        alert('密码不能为空');
        return false;
    }

    var passwordRegex = /^[A-Za-z0-9._@#]{8,16}$/;
    if (!passwordRegex.test(password)) {
        alert('密码格式错误');
        return false;
    }

    if (password !== confirmPassword) {
        alert('密码不一致');
        return false;
    }

    cooldown = true;
    setTimeout(async function() {
        cooldown = false;
    }, 2 * 60 * 1000);
    return true;
}

$(document).ready(function (){
    let user = {
        "id" : 0,
        "name" : null,
        "email" : null,
        "passwd" : null
    }

    var cooldown = false;   // 冷却状态
    var cooldownTime = 0;   // 冷却剩余时间
    $('#auth').click(function (event){
        event.preventDefault();

        var username = $('#username').val();
        var email = $('#email').val();
        if(!validateUsername(username)){
            return;
        }
        if(!validateEmail(email)){
            return;
        }
        if(cooldown){
            alert('验证码已发送，请' + cooldownTime + '秒后再试');
            return;
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            url: "http://127.0.0.1:8080/DiscordLike/user/getAuth",
            contentType: "application/x-www-form-urlencoded",
            data: {"email": email},
            success: function (result){
                alert('验证码已发送');
                cooldown = true;
                cooldownTime = 90;
                var timerInterval = setInterval(function() {
                    cooldownTime--;
                    if (cooldownTime <= 0) {
                        cooldown = false;
                        clearInterval(timerInterval);
                    }
                }, 1000);
            },
            error: function (jqXHR){
                alert('验证码发送失败');
            }
        });
    });

    $('#register').click(function (event){
        event.preventDefault();

        var username = $('#username').val();
        var email = $('#email').val();
        var authCode = $('#auth-code').val();
        var password = $('#password').val();
        var confirmPassword = $('#confirm-password').val();
        if(!validateUsername(username)){
            return;
        }
        if(!validateEmail(email)){
            return;
        }
        if(!validatePassword(password, confirmPassword)){
            return;
        }
        if(authCode === ''){
            alert("验证码不能为空");
            return;
        }
        user.name = username;
        user.email = email;
        user.passwd = password;
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "http://127.0.0.1:8080/DiscordLike/user/register?authCode=" + authCode ,
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function (result){
                window.location.href = '../html/main.html';
            },
            error: function (jqXHR){
                var code = jqXHR.status;
                if(code === 409){
                    alert("用户名或邮箱已被注册");
                }
                else if(code === 401){
                    alert("验证码错误");
                }
                else if(code === 400){
                    alert("请求异常");
                }
                else{
                    alert("网络连接错误");
                }
            }
        })
    });
});