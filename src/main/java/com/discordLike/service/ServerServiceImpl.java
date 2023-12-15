package com.discordLike.service;

import com.discordLike.entity.Server;
import com.discordLike.entity.User;
import com.discordLike.mapper.ServerMapper;
import com.discordLike.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int create(Server server){
        String serverName = server.getName();
        int serverOwnerId = server.getOwner().getId();
        String serverDesc = server.getDescription();
        serverMapper.addServer(serverName, serverOwnerId, serverDesc);

        int serverId = 0;
        try{
            serverId = serverMapper.getLastId();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        serverMapper.addServerToUser(serverId, serverOwnerId);

        return serverId;
    }

    public boolean checkUser(int userId){
        try {
            userMapper.checkUserById(userId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Server getServerInfo(int serverId) {
        return serverMapper.getServerInfo(serverId);
    }

    @Override
    public List<Server> getAllOfUser(int id){
//        try{
            List<Server> list = new ArrayList<>();
            list.addAll(serverMapper.getAllOfUser(id));
            list.addAll(serverMapper.getJoinedOfUser(id));
            return list;
//        }catch (Exception e){
//            return null;
//        }
    }

    public int join(int serverId, User user){
        serverMapper.addJoinerToServer(serverId, user.getId());
        return -1;
    }
}
