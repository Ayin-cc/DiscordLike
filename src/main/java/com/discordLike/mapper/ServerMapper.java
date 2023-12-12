package com.discordLike.mapper;

import com.discordLike.entity.Server;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ServerMapper {
    void addServerToUser(@Param("serverId")int serverId, @Param("userId")int userId);
    void addServer(@Param("name")String name, @Param("owner")int id, @Param("description")String description);
    int getLastId();
    List<Server> getAllOfUser(@Param("id")int id);
}
