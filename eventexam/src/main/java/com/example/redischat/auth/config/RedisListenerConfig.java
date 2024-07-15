package com.example.redischat.auth.config;

import com.example.redischat.redis.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("chatMessage");
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(RedisMessageSubscriber redisMessageSubscriber) {
        return new MessageListenerAdapter(redisMessageSubscriber);
    }

    // 채널로부터 메시지를 수신하여 등록된 리스너에게 전달
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                       MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, new ChannelTopic("chatMessage"));

        return container;
    }
}
