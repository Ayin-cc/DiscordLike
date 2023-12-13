package com.discordLike.controller;


import com.discordLike.entity.Channel;
import com.discordLike.service.ChannelService;
import com.discordLike.service.ChannelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelServiceImpl channelService;

    // 创建频道接口
    @RequestMapping("/create")
    public ResponseEntity<Integer> createChannel(@RequestParam("type")String type, @RequestParam("serverId")int serverId, @RequestBody Channel channel){
        int ret = channelService.createChannel(type, serverId, channel);
        if(ret == -1){
            return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
