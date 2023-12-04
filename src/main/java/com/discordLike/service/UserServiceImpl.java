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
        int ret = 0;
        if(user.getId() != 0){
            ret = userMapper.checkUser(Integer.toString(user.getId()));
        }
        else if(user.getName() != null){
            ret = userMapper.checkUser(user.getName());
        }
        else if(user.getEmail() != null){
            ret = userMapper.checkUser(user.getEmail());
        }
        return ret;
    }

    @Override
    public boolean login(int id, String passwd){
        if(userMapper.checkPasswd(id, passwd) == 1){
            return true;
        }
        return false;
    }
}
