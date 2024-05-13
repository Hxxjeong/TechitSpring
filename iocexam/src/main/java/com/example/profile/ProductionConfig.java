package com.example.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 배포 환경용 설정
@Configuration
@Profile("prod")
public class ProductionConfig {
    @Bean
    public DataService dataService() {
        return new DataService("Production Environment");
    }
}
