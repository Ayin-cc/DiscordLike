package com.discordLike.controller;

import com.discordLike.entity.Channel;
import com.discordLike.entity.Message;
import com.discordLike.service.ChannelServiceImpl;
import com.discordLike.service.MessageServiceImpl;
import jakarta.websocket.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelServiceImpl channelService;
    @Autowired
    private MessageServiceImpl messageService;

    // 创建频道接口
    @RequestMapping("/create")
    public ResponseEntity<Integer> createChannel(@RequestParam("type")String type, @RequestParam("serverId")int serverId, @RequestBody Channel channel){
        int ret = channelService.createChannel(type, serverId, channel);
        if(ret == -1){
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    // 获取文本频道历史消息
    @RequestMapping("/text/history")
    public ResponseEntity<List<Message>> queryHistory(@RequestParam("channelId")int channelId){
        List<Message> messageList = new ArrayList<>();
        for(int id : channelService.historyMsgId(channelId)){
            messageList.add(messageService.getMessage(id));
        }
        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }
}
