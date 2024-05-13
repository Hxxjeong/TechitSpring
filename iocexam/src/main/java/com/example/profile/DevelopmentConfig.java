package com.example.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 개발 환경용 설정
@Configuration
@Profile("dev")
public class DevelopmentConfig {
    @Bean
    public DataService dataService() {
        return new DataService("Development Environment");
    }
}
