package com.discordLike.service;

import com.discordLike.entity.Message;

public interface MessageService {
    int addMessage(Message message, int channelId);
    Message getMessage(int id);
}
