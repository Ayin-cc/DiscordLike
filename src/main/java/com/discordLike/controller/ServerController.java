package com.discordLike.controller;

import com.discordLike.entity.Server;
import com.discordLike.entity.User;
import com.discordLike.service.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServerServiceImpl serverService;

    // 创建服务器接口
    @RequestMapping("/create")
    public ResponseEntity<Integer> createServer(@RequestBody Server server){
        int ret = serverService.create(server);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    // 删除服务器接口
    @RequestMapping("/delete")
    public ResponseEntity<Integer> deleteServer(@RequestParam("serverId") int serverId, @RequestBody User user){
        if(serverService.checkUser(user.getId()) == -1){
            return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
        }
        if(serverService.checkServer(serverId) == -1){
            return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
        }
        // 判断是否为服务器创建者
        if(!serverService.isOwner(serverId, user.getId())) {
            // 判断是否为服务器加入者
            if(!serverService.isJoiner(serverId, user.getId())) {
                return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
            }
        }

        // 执行删除
        serverService.delete(serverId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    // 获取服务器列表接口
    @RequestMapping("/getList")
    public ResponseEntity<List<Server>> getServerList(@RequestParam int id){
        List<Server> servers = serverService.getAllOfUser(id);
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    // 获取服务器详情接口
    @RequestMapping("/getInfo")
    public ResponseEntity<Server> getServerInfo(@RequestParam int userId, @RequestParam int serverId){
        if(serverService.checkUser(userId) == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Server ret = serverService.getServerInfo(serverId);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    // 用户加入服务器接口
    @RequestMapping("/join")
    public ResponseEntity<Integer> joinServer(@RequestParam("serverId")int serverId, @RequestBody User user){
        if(serverService.checkUser(user.getId()) == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(serverService.checkServer(serverId) == -1){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        serverService.join(serverId, user);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
}
