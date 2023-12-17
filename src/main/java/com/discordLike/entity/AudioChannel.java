package com.discordLike.entity;

import java.util.List;

public class AudioChannel extends Channel{
    private int id;
    private String name;
    private User owner;
    private String description;
    private List<User> onlineUsers;

    public AudioChannel() {
    }

    public AudioChannel(Channel channel){
        this.id = channel.getId();
        this.name = channel.getName();
        this.description = channel.getDescription();
        this.owner = channel.getOwner();
    }

    public AudioChannel(int id, String name, User owner, String description, List<User> onlineUsers) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.onlineUsers = onlineUsers;
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
     * @return onlineUsers
     */
    public List<User> getOnlineUsers() {
        return onlineUsers;
    }

    /**
     * 设置
     * @param onlineUsers
     */
    public void setOnlineUsers(List<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    @Override
    public String toString() {
        return "AudioChannel{id = " + id + ", name = " + name + ", owner = " + owner + ", description = " + description + ", onlineUsers = " + onlineUsers + "}";
    }
}
