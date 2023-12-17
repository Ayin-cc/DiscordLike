package com.discordLike.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Conversation {
    private int fromId;
    private int toId;
    private String content;
    private LocalDateTime time;

    public Conversation() {
    }

    public Conversation(int fromId, int toId, String content, LocalDateTime time) {
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.time = time;
    }

    /**
     * 获取
     * @return fromId
     */
    public int getFromId() {
        return fromId;
    }

    /**
     * 设置
     * @param fromId
     */
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    /**
     * 获取
     * @return toId
     */
    public int getToId() {
        return toId;
    }

    /**
     * 设置
     * @param toId
     */
    public void setToId(int toId) {
        this.toId = toId;
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
        return "Conversation{fromId = " + fromId + ", toId = " + toId + ", content = " + content + ", time = " + time + "}";
    }
}
