package com.discordLike.mapper;

import com.discordLike.entity.Channel;
import com.discordLike.entity.Message;
import com.discordLike.entity.TextChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TextChannelMapper {
    void addChannel(@Param("channel")Channel channel);
    void addChannelToServer(@Param("serverId")int serverId, @Param("channelId")int channelId);
    TextChannel getChannelById(@Param("id")int id);
    List<Integer> getAllMessageId(@Param("channelId")int channelId);
    int getLastId();
    void deleteById(@Param("id")int id);
    void deleteHistoryOfId(@Param("id")int id);
    void deleteChannelOfServer(@Param("id")int id);
}
