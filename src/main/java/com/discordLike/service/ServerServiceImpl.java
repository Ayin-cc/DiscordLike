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
        try {
            int serverId = serverMapper.getLastId();
            serverMapper.addServerToUser(serverId, serverOwnerId);
            return serverId;
        }catch (Exception e){
            return -1;
        }
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
