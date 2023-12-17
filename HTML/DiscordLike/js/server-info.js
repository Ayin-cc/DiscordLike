$(document).ready(function (){
    var server = JSON.parse(sessionStorage.getItem("server"));

    $('#server-name').text(server.name);
    $('#server-id').text("ID: " + server.id);
    $('#server-description').text("描述: " + server.description);

    var textChannels = server.textChannels;
    var audioChannels = server.audioChannels;
    $('.channel').remove();
    for(let channel of textChannels){
        let newChannel = $("<div class='channel'>#" + channel.id + " " + channel.name + "</div>");
        $('.text-channel').append(newChannel);
    }
    for(let channel of audioChannels){
        let newChannel = $("<div class='channel'>#" + channel.id + " " + channel.name + "</div>");
        $('.audio-channel').append(newChannel);
    }
});