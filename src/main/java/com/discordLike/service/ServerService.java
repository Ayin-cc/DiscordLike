package com.discordLike.service;

import com.discordLike.entity.Server;

import java.util.ArrayList;
import java.util.List;

public interface ServerService {
    int create(Server server);
    int delete(int serverId);
    Server getServerInfo(int serverId);
    List<Server> getAllOfUser(int id);
}
