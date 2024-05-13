package com.example.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// default 환경
@Configuration
@Profile("default")
public class DefaultConfig {
    @Bean
    public DataService dataService() {
        return new DataService("Default Environment");
    }
}
