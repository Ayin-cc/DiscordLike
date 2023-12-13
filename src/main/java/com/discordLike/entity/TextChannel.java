package com.discordLike.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TextChannel extends Channel{
    private int id;
    private String name;
    private User owner;
    private String description;
    private List<SubChannel> subChannels;
    private List<Message> messages;

    public TextChannel() {
    }

    public TextChannel(Channel channel){
        this.id = channel.getId();
        this.name = channel.getName();
        this.description = channel.getDescription();
        this.owner = channel.getOwner();
    }

    public TextChannel(int id, String name, User owner, String description, List<SubChannel> subChannels, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.subChannels = subChannels;
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
     * @return subChannels
     */
    public List<SubChannel> getSubChannels() {
        return subChannels;
    }

    /**
     * 设置
     * @param subChannels
     */
    public void setSubChannels(List<SubChannel> subChannels) {
        this.subChannels = subChannels;
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
        return "TextChannel{id = " + id + ", name = " + name + ", owner = " + owner + ", description = " + description + ", subChannels = " + subChannels + ", messages = " + messages + "}";
    }
}