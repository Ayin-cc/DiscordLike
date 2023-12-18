package com.discordLike.controller;

import com.discordLike.component.ChatSocketServer;
import com.discordLike.entity.Channel;
import com.discordLike.entity.Message;
import com.discordLike.service.ChannelServiceImpl;
import com.discordLike.service.MessageServiceImpl;
import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ChannelController.class);

    @Autowired
    private ChannelServiceImpl channelService;
    @Autowired
    private MessageServiceImpl messageService;

    // 创建频道接口
    @RequestMapping("/create")
    public ResponseEntity<Integer> createChannel(@RequestParam("type")String type, @RequestParam("serverId")int serverId, @RequestBody Channel channel){
        int ret = channelService.createChannel(type, serverId, channel);
        if(ret == -1){
            log.error("【ChannelController】频道创建请求错误:" + channel.toString());
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        }
        log.info("【ChannelController】频道" + ret + "创建成功");
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    // 获取频道信息
    @RequestMapping("/text/info")
    public ResponseEntity<Channel> getTextChannelInfo(@RequestParam("id")int id){
        log.info("【ChannelController】获取频道" + id + "信息");
        Channel channel = channelService.getChannel(id, true);
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }
    @RequestMapping("/audio/info")
    public ResponseEntity<Channel> getAudioChannelInfo(@RequestParam("id")int id){
        log.info("【ChannelController】获取频道" + id + "信息");
        Channel channel = channelService.getChannel(id, false);
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }

    // 获取文本频道历史消息
    @RequestMapping("/text/history")
    public ResponseEntity<List<Message>> queryHistory(@RequestParam("channelId")int channelId){
        log.info("【ChannelController】获取频道" + channelId + "历史消息");
        List<Message> messageList = new ArrayList<>();
        for(int id : channelService.historyMsgId(channelId)){
            messageList.add(messageService.getMessage(id));
        }
        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }

    // 删除频道
    @RequestMapping("/text/delete")
    public ResponseEntity<Integer> deleteChannel(@RequestParam("channelId")int channelId){
        log.info("【ChannelController】删除频道" + channelId);
        channelService.deleteChannel(channelId, true);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
}
