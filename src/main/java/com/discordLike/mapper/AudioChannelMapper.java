package com.discordLike.mapper;

import com.discordLike.entity.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AudioChannelMapper {
    void addChannel(@Param("channel") Channel channel);
    void addChannelToServer(@Param("serverId")int serverId, @Param("channelId")int channelId);
    int getLastId();
}
