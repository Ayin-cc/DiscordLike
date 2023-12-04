package com.discordLike.mapper;

import com.discordLike.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int checkUser(@Param("id")String id);
    int checkPasswd(@Param("id")int id, @Param("passwd")String passwd);
}
