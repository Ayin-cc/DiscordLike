package com.discordLike.service;

import com.discordLike.entity.Server;

import java.util.ArrayList;
import java.util.List;

public interface ServerService {
    int create(Server server);
    List<Server> getAllOfUser(int id);
}
