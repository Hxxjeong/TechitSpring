package com.example.message2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServiceConfig.class)
public class MainConfig {
    // 추가적인 설정
    
    MainConfig() {
        System.out.println("MainConfig 생성");
    }
}
