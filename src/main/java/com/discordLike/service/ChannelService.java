package com.discordLike.service;

import com.discordLike.entity.Channel;

public interface ChannelService {
    int createChannel(String type, int serverId, Channel channel);
    Channel getChannel(int id, boolean isTextChannel);
    int deleteChannel(int channelId, boolean isTextChannel);
}
