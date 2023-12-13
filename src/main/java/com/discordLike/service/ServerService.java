package com.discordLike.service;

import com.discordLike.entity.Server;

import java.util.ArrayList;
import java.util.List;

public interface ServerService {
    int create(Server server);
    Server getServerInfo(int userId, int serverId);
    List<Server> getAllOfUser(int id);
}
