package com.example.eventexam2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // ThreadPool 크기
        executor.setMaxPoolSize(10);    // 최대 Pool 크기
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("CustomExecutor-"); // Thread name
        executor.initialize();
        return executor;
    }
}
