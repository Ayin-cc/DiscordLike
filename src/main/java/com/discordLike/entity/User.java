package com.discordLike.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User {
    private int id;
    private String name;
    private String passwd;
    private byte[] photo;
    private String email;
    private List<Server> servers;
    private List<User> friends;


    public User() {
    }

    public User(int id, String name, String passwd, byte[] photo, String email, List<Server> servers, List<User> friends) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
        this.photo = photo;
        this.email = email;
        this.servers = servers;
        this.friends = friends;
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
     * @return passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 设置
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return servers
     */
    public List<Server> getServers() {
        return servers;
    }

    /**
     * 设置
     * @param servers
     */
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    /**
     * 获取
     * @return friends
     */
    public List<User> getFriends() {
        return friends;
    }

    /**
     * 设置
     * @param friends
     */
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{id = " + id + ", name = " + name + ", passwd = " + passwd + ", photo = " + photo + ", email = " + email + ", servers = " + servers + ", friends = " + friends + "}";
    }
}
