package com.discordLike.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.discordLike.entity.Message;
import com.discordLike.service.MessageServiceImpl;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/channel/{cid}/{userId}")
@Component
public class ChatSocketServer {
    private static ApplicationContext context;
    private MessageServiceImpl messageService;

    private int userId = 0;
    private int cid = 0;
    private static final Logger log = LoggerFactory.getLogger(ChatSocketServer.class);
    private static final Map<Integer, Map<Integer, Session>> sessionPool = new ConcurrentHashMap<>();

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        ChatSocketServer.context = context;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value="cid")int cid, @PathParam(value = "userId")int userId) {
        this.messageService = context.getBean(com.discordLike.service.MessageServiceImpl.class);
        try {
            this.userId = userId;
            this.cid = cid;
            sessionPool.computeIfAbsent(cid, k -> new ConcurrentHashMap<>()).put(userId, session);
            log.info("【websocket消息】频道" + this.cid + "有新的连接，总数为:" + sessionPool.get(cid).size());

            // 发送问候信息
            sendOneMessage(cid ,this.userId, JSON.toJSONString(Message.systemMessage("Hello")));
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose() {
        try {
            sessionPool.remove(this.userId);
            log.info("【websocket消息】频道" + this.cid + "连接断开，总数为:" + (sessionPool.get(cid).size() - 1));
        } catch (Exception e) {
        }
    }

    @OnMessage
    public void onMessage(String message) {


        log.info("【websocket消息】频道" + this.cid + "收到客户端消息:" + message);
        JSONObject obj = JSONObject.parseObject(message);
        Message msg = JSON.toJavaObject(obj, com.discordLike.entity.Message.class);
        // 判断是聊天还是私聊
        String type = obj.getString("type");
        if (type.equals("conversation")){
            // TODO 私聊
        }
        else if (type.equals("message")) {
            // 存入数据库
            messageService.addMessage(msg, cid);

            // 广播消息
            sendAllMessage(message);
        }
    }

    // 广播消息
    public void sendAllMessage(String message) {
        log.info("【websocket消息】频道" + this.cid + "广播消息:" + message);
        Map<Integer, Session> online = sessionPool.get(this.cid);
        for(int id : online.keySet()) {
            Session session = online.get(id);
            session.getAsyncRemote().sendText(message);
        }
    }

    // 单点消息
    public void sendOneMessage(int cid, int userId, String message) {
        Session session = sessionPool.get(cid).get(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("【websocket消息】To用户" + userId + "单点消息:" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
