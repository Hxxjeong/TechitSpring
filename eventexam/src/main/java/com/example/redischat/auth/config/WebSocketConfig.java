package com.example.redischat.auth.config;

import com.example.redischat.websocket.CustomHandshakeInterceptor;
import com.example.redischat.websocket.MyWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final CustomHandshakeInterceptor customHandshakeInterceptor;
    private final MyWebSocketHandler myWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/ws")
                .setAllowedOrigins("*")
                .addInterceptors(customHandshakeInterceptor);
    }
}
