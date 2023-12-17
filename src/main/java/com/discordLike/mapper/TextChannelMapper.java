package com.discordLike.mapper;

import com.discordLike.entity.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TextChannelMapper {
    void addChannel(@Param("channel")Channel channel);
    void addChannelToServer(@Param("serverId")int serverId, @Param("channelId")int channelId);
    List<Integer> getAllMessageId(@Param("channelId")int channelId);
    int getLastId();
}
