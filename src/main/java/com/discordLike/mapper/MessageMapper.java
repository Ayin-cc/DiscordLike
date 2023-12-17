package com.discordLike.mapper;

import com.discordLike.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Mapper
public interface MessageMapper {
    void addMessage(@Param("fromId")int fromId, @Param("content")String content, @Param("time")LocalDateTime time);
    void addMessageToChannel(@Param("msgId")int msgId, @Param("channelId")int channelId);
    Message getMessageById(@Param("id")int id);
    int getLastInsertId();
}
