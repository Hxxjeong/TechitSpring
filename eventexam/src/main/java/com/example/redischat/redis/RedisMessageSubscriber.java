package com.example.redischat.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    private final RedisTemplate<String, String> redisTemplate;

    public static void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public static void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = (String) redisTemplate.getValueSerializer().deserialize(message.getBody());

        for(WebSocketSession session : sessions) {
            if(session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(msg));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
