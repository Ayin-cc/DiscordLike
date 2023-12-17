$(document).ready(function (){
    var userInfo = JSON.parse(sessionStorage.getItem("user"));

    $('#user-name').text(userInfo.name);
    $('#user-email').text(userInfo.email);
    $('#user-id').text("ID: " + userInfo.id);

    $('#deleteAccount').click(function (){
        console.log("click");
       var conf = confirm("确定要注销账号吗");
       if(conf){
           var passwd = prompt("请输入密码以确认");
           var user = {
               "id": userInfo.id,
               "passwd": passwd
           }
           console.log(JSON.stringify(user));
           $.ajax({
               type: "POST",
               dataType: "json",
               url: "/DiscordLike/user/delete",
               contentType: "application/json",
               data: JSON.stringify(user),
               success: function (result){
                   alert("删除成功");
                   sessionStorage.clear();
                   parent.window.location.href = "../html/main.html";
               },
               error: function (jqXHR){
                   var code = jqXHR.status;
                   if(code === 400){
                       alert("密码错误，删除失败");
                   }
               }
           })
       }
    });
});