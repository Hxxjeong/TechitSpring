package com.example.redisexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveJsonWithTTL(String key, String json, long ttlInSeconds) {
        redisTemplate.opsForValue().set(key, json, Duration.ofSeconds(ttlInSeconds));
    }

    public String getJson(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
