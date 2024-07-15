package com.example.redischat.websocket;

import com.example.redischat.redis.RedisMessagePublisher;
import com.example.redischat.redis.RedisMessageSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MyWebSocketHandler extends TextWebSocketHandler {
    private final RedisMessagePublisher redisMessagePublisher;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established with session: " + session.getId());
        RedisMessageSubscriber.addSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // 현재 인증된 사용자 정보 가져오기
        SecurityContext context = (SecurityContext) session.getAttributes().get("SPRING_SECURITY_POLICY");
        String username = "Unknown User";

        if(context != null
                && context.getAuthentication() != null
                && context.getAuthentication().getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
            username = userDetails.getUsername();
        }

        redisMessagePublisher.publish(username + ": " + payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        RedisMessageSubscriber.removeSession(session);
        System.out.println("Connection closed with session: " + session.getId());
    }
}