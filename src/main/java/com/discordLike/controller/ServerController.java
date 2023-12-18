package com.discordLike.controller;

import com.discordLike.entity.Server;
import com.discordLike.entity.User;
import com.discordLike.service.ServerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/server")
public class ServerController {
    private static final Logger log = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private ServerServiceImpl serverService;

    // 创建服务器接口
    @RequestMapping("/create")
    public ResponseEntity<Integer> createServer(@RequestBody Server server){
        int ret = serverService.create(server);
        log.info("【ServerController】服务器" + ret + "创建成功");
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
                log.error("【ServerController】未知用户" + user.getId() + "试图删除服务器" + serverId);
                return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
            }

            // 为用户移除服务器
            serverService.removeFromUser(serverId, user.getId());
            log.info("【ServerController】用户" + user.getId() + "移除加入的服务器" + serverId);
            return new ResponseEntity<>(200, HttpStatus.OK);
        }

        // 执行删除
        serverService.delete(serverId);
        log.info("【ServerController】用户" + user.getId() + "移除创建的服务器" + serverId);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    // 获取服务器列表接口
    @RequestMapping("/getList")
    public ResponseEntity<List<Server>> getServerList(@RequestParam("id") int id){
        log.info("【ServerController】获取用户" + id + "的服务器列表");
        List<Server> servers = serverService.getAllOfUser(id);
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    // 获取服务器详情接口
    @RequestMapping("/getInfo")
    public ResponseEntity<Server> getServerInfo(@RequestParam("userId") int userId, @RequestParam("serverId") int serverId){
        if(serverService.checkUser(userId) == -1){
            log.error("【ServerController】未知用户" + userId + "试图获取服务器" + serverId + "信息");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        log.info("【ServerController】获取服务器" + serverId + "信息");
        Server ret = serverService.getServerInfo(serverId);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    // 用户加入服务器接口
    @RequestMapping("/join")
    public ResponseEntity<Integer> joinServer(@RequestParam("serverId")int serverId, @RequestBody User user){
        if(serverService.checkUser(user.getId()) == -1){
            log.error("【ServerController】未知用户" + user.getId() + "试图加入服务器" + serverId);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(serverService.checkServer(serverId) == -1){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(serverService.isJoiner(serverId, user.getId())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        if(serverService.isOwner(serverId, user.getId())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        log.info("【ServerController】用户" + user.getId() + "加入服务器" + serverId);
        serverService.join(serverId, user);
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
}
