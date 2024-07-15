package com.example.securitychat.websocket;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established with session: " + session.getId());
        sessions.add(session);
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

        // sessions가 동기화 되어 있기 때문에 synchronized 블록이 생략되어도 됨
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {    // 세션이 열려있을 때만 전송
                webSocketSession.sendMessage(new TextMessage(username + ": " + payload));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed with session: " + session.getId());
        sessions.remove(session);
    }
}