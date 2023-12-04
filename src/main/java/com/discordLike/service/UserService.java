package com.discordLike.service;

import com.discordLike.entity.User;

public interface UserService {
    int checkUser(User user);
    boolean login(int id, String passwd);
}
