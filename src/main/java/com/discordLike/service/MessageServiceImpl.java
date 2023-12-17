package com.discordLike.service;

import com.discordLike.entity.Message;
import com.discordLike.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int addMessage(Message message, int channelId) {
        try {
            messageMapper.addMessage(message.getSender().getId(), message.getContent(), message.getTime());
            int msgId = messageMapper.getLastInsertId();
            messageMapper.addMessageToChannel(msgId, channelId);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Message getMessage(int id) {
        try{
            return messageMapper.getMessageById(id);
        }catch (Exception e){
            return null;
        }
    }
}
