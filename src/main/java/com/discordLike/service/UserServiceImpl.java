package com.discordLike.service;

import com.discordLike.entity.Server;
import com.discordLike.entity.User;
import com.discordLike.mapper.ServerMapper;
import com.discordLike.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public int checkUser(User user){
        int id = user.getId();
        String username = user.getName();
        String email = user.getEmail();

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
        return userMapper.checkPasswd(id, passwd) == 1;
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
    public int delete(int id){
        try{
            userMapper.deleteUser(id);
            for(Server server : serverMapper.getAllOfUser(id)){
                // 删除拥有的服务器
                int serverId = server.getId();
                serverMapper.deleteServer(serverId);
                serverMapper.deleteJoinerOfServer(serverId);
                serverMapper.deleteUser(serverId);
            }
            for(Server server : serverMapper.getJoinedOfUser(id)){
                // 从加入的服务器中删除
                int serverId = server.getId();
                serverMapper.deleteJoiner(serverId, id);
            }
            return 1;
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
