package com.discordLike.controller;

import com.discordLike.entity.Server;
import com.discordLike.entity.User;
import com.discordLike.service.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServerServiceImpl serverService;

    // 创建接口
    @RequestMapping("/create")
    public ResponseEntity<Integer> createServer(@RequestBody Server server){

        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    // 获取服务器列表接口
    @RequestMapping("/getList")
    public ResponseEntity<List<Server>> getServerList(@RequestParam int id){
        Server server = new Server();
        server.setId(123);
        server.setName("test");
        List<Server> servers = new ArrayList<>();
        servers.add(server);
        Server server2 = new Server();
        server2.setId(233);
        server2.setName("prpr");
        servers.add(server2);
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    // 获取服务器详情接口
    @RequestMapping("/getInfo")
    public ResponseEntity<Server> getServerInfo(@RequestParam int userId, @RequestParam int serverId){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
