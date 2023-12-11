package com.discordLike.mapper;

import com.discordLike.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int checkUser(@Param("id")int id);
    int checkUser(@Param("id")String id);
    int checkPasswd(@Param("id")int id, @Param("passwd")String passwd);
    int getLastId();
    void addUser(@Param("user")User user);
    User getUser(@Param("id")int id);
    User getUser(@Param("id")String id);
}
