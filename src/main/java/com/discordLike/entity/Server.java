package com.discordLike.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Server {
    private int id;
    private String serverName;
    private User owner;
    private byte[] photo;
    private String code;
    private List<TextChannel> textChannels;
    private List<AudioChannel> audioChannels;

    public Server() {
    }

    public Server(int id, String serverName, User owner, byte[] photo, String code, List<TextChannel> textChannels, List<AudioChannel> audioChannels) {
        this.id = id;
        this.serverName = serverName;
        this.owner = owner;
        this.photo = photo;
        this.code = code;
        this.textChannels = textChannels;
        this.audioChannels = audioChannels;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * 设置
     * @param serverName
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * 获取
     * @return owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * 设置
     * @param owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * 获取
     * @return photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * 设置
     * @param photo
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     * @return textChannels
     */
    public List<TextChannel> getTextChannels() {
        return textChannels;
    }

    /**
     * 设置
     * @param textChannels
     */
    public void setTextChannels(List<TextChannel> textChannels) {
        this.textChannels = textChannels;
    }

    /**
     * 获取
     * @return audioChannels
     */
    public List<AudioChannel> getAudioChannels() {
        return audioChannels;
    }

    /**
     * 设置
     * @param audioChannels
     */
    public void setAudioChannels(List<AudioChannel> audioChannels) {
        this.audioChannels = audioChannels;
    }

    @Override
    public String toString() {
        return "Server{id = " + id + ", serverName = " + serverName + ", owner = " + owner + ", photo = " + photo + ", code = " + code + ", textChannels = " + textChannels + ", audioChannels = " + audioChannels + "}";
    }
}
