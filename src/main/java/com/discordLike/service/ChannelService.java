package com.discordLike.service;

import com.discordLike.entity.Channel;

public interface ChannelService {
    int createChannel(String type, int serverId, Channel channel);
}
