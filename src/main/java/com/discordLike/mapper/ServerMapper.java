package com.discordLike.mapper;

import com.discordLike.entity.Server;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ServerMapper {
    void addJoinerToServer(@Param("serverId")int serverId, @Param("userId")int userId);
    void addServerToUser(@Param("serverId")int serverId, @Param("userId")int userId);
    void addServer(@Param("name")String name, @Param("owner")int id, @Param("description")String description);
    int getLastId();
    int checkServer(@Param("serverId")int serverId);
    int checkServerOfUser(@Param("userId")int userId, @Param("serverId")int serverId);
    int checkOwner(@Param("serverId")int serverId, @Param("userId")int userId);
    int checkJoiner(@Param("serverId")int serverId, @Param("userId")int userId);
    Server getServerInfo(@Param("serverId")int serverId);
    List<Server> getAllOfUser(@Param("id")int id);
    List<Server> getJoinedOfUser(@Param("id")int id);
    void deleteServer(@Param("serverId")int serverId);
    void deleteJoinerOfServer(@Param("serverId")int serverID);
    void deleteJoiner(@Param("serverId")int serverId, @Param("userId")int userId);
    void deleteUser(@Param("serverId")int serverId);
}
