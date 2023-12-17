package com.discordLike.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Server {
    private int id;
    private String name;
    private User owner;
    private byte[] photo;
    private String code;
    private String description;
    private List<TextChannel> textChannels;
    private List<AudioChannel> audioChannels;


    public Server() {
    }

    public Server(int id, String name, User owner, byte[] photo, String code, String description, List<TextChannel> textChannels, List<AudioChannel> audioChannels) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.photo = photo;
        this.code = code;
        this.description = description;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
        return "Server{id = " + id + ", name = " + name + ", owner = " + owner + ", photo = " + photo + ", code = " + code + ", description = " + description + ", textChannels = " + textChannels + ", audioChannels = " + audioChannels + "}";
    }
}
