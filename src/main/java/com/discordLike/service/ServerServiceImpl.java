package com.discordLike.service;

import com.discordLike.entity.Server;
import com.discordLike.mapper.ServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerMapper serverMapper;

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

    @Override
    public Server getServerInfo(int userId, int serverId) {
        if(serverMapper.checkServerOfUser(userId, serverId) != 1){
            return null;
        }
        return serverMapper.getServerInfo(serverId);
    }

    @Override
    public List<Server> getAllOfUser(int id){
        try{
            return serverMapper.getAllOfUser(id);
        }catch (Exception e){
            return null;
        }
    }
}
