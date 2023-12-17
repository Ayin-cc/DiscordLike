package com.discordLike.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Message {
    private int id;
    private User sender;
    private String content;
    private LocalDateTime time;

    public Message() {
    }

    public Message(int id, User sender, String content, LocalDateTime time) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.time = time;
    }

    public static Message systemMessage(String content){
        Message sysMsg = new Message();
        User user = new User();
        user.setId(0);
        user.setName("System");
        sysMsg.sender = user;
        sysMsg.content = content;
        sysMsg.time = LocalDateTime.now();
        return sysMsg;
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
     * @return sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * 设置
     * @param sender
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{id = " + id + ", sender = " + sender + ", content = " + content + ", time = " + time + "}";
    }
}
