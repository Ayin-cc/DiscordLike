package com.discordLike.service;

import com.discordLike.entity.User;

public interface UserService {
    int checkUser(User user);
    boolean login(int id, String passwd);
    int register(User user);
    int delete(int serverId);
    User getUser(int id);
    User getUser(String usernameOrEmail);
}
