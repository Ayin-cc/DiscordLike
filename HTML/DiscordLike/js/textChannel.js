$(document).ready(function (){
    var user = JSON.parse(sessionStorage.getItem("user"));
    var channelId = new URLSearchParams(window.location.search).get('id');

    $("#messageInput").on("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            $("#send-btn").click();
        }
    });

    // 获取频道详情
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/DiscordLike/channel/text/info?id=" + channelId.toString(),
        contentType: "application/json",
        success: function (result){
            $('#channel-name').text(result.name);
            $('#channel-id').text(result.id);
            $('#channel-owner-name').text(result.owner.name);
            if(result.owner.id !== user.id){
                $('.delete-button').hide();
            }
        }
    });

    // 获取消息列表
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "/DiscordLike/channel/text/history?channelId=" + channelId.toString(),
        contentType: "application/json",
        success: function (result){
            for(let message of result){
                if(message.sender.id === user.id){
                    addMessage(message.sender.name, message.content, message.time, "my-message");
                }else if(message.sender.id === 0){
                    addMessage(message.sender.name, message.content, message.time, "sys-message");
                }else{
                    addMessage(message.sender.name, message.content, message.time, "their-message");
                }
            }
        }
    });

    var url = SOCKET + '/DiscordLike/channel/' + channelId.toString() + '/' + user.id.toString();
    var ws = new WebSocket(url);

    $('#send-btn').click(function (){
        var content = $('#messageInput').val() || '';
        var message = {
            "type": "message",
            "sender": {
                "id": user.id,
                "name": user.name
            },
            "content": content,
            "time": Date.now()
        }
        console.log(JSON.stringify(message));
        ws.send(JSON.stringify(message));
        $('#messageInput').val('');
    });

    $('.delete-button').click(function (){
        var conf = confirm("确认删除吗");
        if(conf){
            var cid = prompt("请输入该频道id以确认");
            if(cid !== channelId){
                alert("输入错误");
                return;
            }
        }

        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/DiscordLike/channel/text/delete?channelId=" + channelId.toString(),
            contentType: "application/json",
            success: function (result){
                alert("删除成功");
                parent.location.reload();
            }
        });
    });


    // WebSocket接收到消息
    ws.onmessage = function (event) {
        var data = JSON.parse(event.data);
        var type = "their-message";
        if(data.sender.id === user.id){
            type = "my-message";
        }
        else if(data.sender.id === 0){
            type = "sys-message";
        }
        addMessage(data.sender.name, data.content, data.time, type);
    };

    // 添加消息到页面
    function addMessage(name, content, time, messageType) {
        var messageList = $('#messageList');
        var messageElement = $('<div class="message ' + messageType + '">')
            //.append('<img src="' + avatar + '" alt="Avatar" class="avatar">')
            .append('<span class="name">' + name + '</span>')
            .append('<span class="message-content">' + content + '</span>');
        messageList.append(messageElement);
        messageList.scrollTop(messageList.prop('scrollHeight')); // 滚动到底部
    }

    function deleteChannel() {
        alert('Channel deleted!');
    }

});