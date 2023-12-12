package com.discordLike.service;

import com.discordLike.entity.User;
import com.discordLike.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int checkUser(User user){
        int id = user.getId();
        String username = user.getName();
        String email = user.getEmail();

        System.out.println(id);
        System.out.println(username);
        System.out.println(email);
        if(id != 0){
            return userMapper.checkUserById(id);
        }
        if(username != null || email != null){
            return userMapper.checkUser(username, email);
        }

        return 0;
    }

    @Override
    public boolean login(int id, String passwd){
        if(userMapper.checkPasswd(id, passwd) == 1){
            return true;
        }
        return false;
    }

    @Override
    public int register(User user){
        userMapper.addUser(user);
        try{
            return userMapper.getLastId();
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public User getUser(int id){
        return userMapper.getUser(id);
    }

    @Override
    public User getUser(String usernameOrEmail){
        return userMapper.getUser(usernameOrEmail);
    }

    public boolean checkAuth(String authCode){
        return true;
    }
}
