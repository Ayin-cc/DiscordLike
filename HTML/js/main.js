$(document).ready(function (){
    // 获取登录状态
    var isLoggedIn = sessionStorage.getItem("login");
    console.log(isLoggedIn);

    // 检测登录状态
    if (!isLoggedIn) {
        // 未登录状态，将侧边栏导航置为灰色不可点击
    } else {
        // 登录状态，展示个人信息和频道
        $('#show-page').attr("src", "user.html");
    }
});