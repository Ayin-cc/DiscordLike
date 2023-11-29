package com.discordLike.entity;

import java.util.List;

public class SubChannel {
    private int id;
    private String name;
    private User owner;
    private TextChannel mainChannel;
    private List<Message> messages;

    public SubChannel() {
    }

    public SubChannel(int id, String name, User owner, TextChannel mainChannel, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.mainChannel = mainChannel;
        this.messages = messages;
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
     * @return mainChannel
     */
    public TextChannel getMainChannel() {
        return mainChannel;
    }

    /**
     * 设置
     * @param mainChannel
     */
    public void setMainChannel(TextChannel mainChannel) {
        this.mainChannel = mainChannel;
    }

    /**
     * 获取
     * @return messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * 设置
     * @param messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "SubChannel{id = " + id + ", name = " + name + ", owner = " + owner + ", mainChannel = " + mainChannel + ", messages = " + messages + "}";
    }
}
