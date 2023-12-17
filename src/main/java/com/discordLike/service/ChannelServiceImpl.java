package com.discordLike.service;

import com.discordLike.entity.Channel;
import com.discordLike.mapper.AudioChannelMapper;
import com.discordLike.mapper.SubChannelMapper;
import com.discordLike.mapper.TextChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private AudioChannelMapper audioChannelMapper;
    @Autowired
    private TextChannelMapper textChannelMapper;
    @Autowired
    private SubChannelMapper subChannelMapper;

    @Override
    public int createChannel(String type, int serverId, Channel channel) {
        int id = -1;
        if(type.equals("text")){
            textChannelMapper.addChannel(channel);
            try {
                id = textChannelMapper.getLastId();
                textChannelMapper.addChannelToServer(serverId, id);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        else if(type.equals("audio")){
            audioChannelMapper.addChannel(channel);
            try {
                id = audioChannelMapper.getLastId();
                textChannelMapper.addChannelToServer(serverId, id);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        else{
            return -1;
        }
        return id;
    }

    @Override
    public Channel getChannel(int id, boolean isTextChannel) {
        try{
            if (isTextChannel) {
                return textChannelMapper.getChannelById(id);
            } else {
                return audioChannelMapper.getChannelById(id);
            }
        }catch (Exception e) {
            return null;
        }
    }

    public List<Integer> historyMsgId(int channelId){
        try{
            return textChannelMapper.getAllMessageId(channelId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteChannel(int channelId, boolean isTextChannel){
        //try{
            if(isTextChannel){
                textChannelMapper.deleteById(channelId);
                textChannelMapper.deleteChannelOfServer(channelId);
                textChannelMapper.deleteHistoryOfId(channelId);
            }
            else {
                audioChannelMapper.deleteById(channelId);
                audioChannelMapper.deleteChannelOfServer(channelId);
            }
            return 1;
//        }catch (Exception e){
//            return -1;
//        }
    }
}
