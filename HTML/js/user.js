$(document).ready(function (){
    var user = JSON.parse(sessionStorage.getItem("user"));

    $('#user-name').text(user.name);
    $('#user-email').text(user.email);
    $('#user-id').text("ID: " + user.id);
});