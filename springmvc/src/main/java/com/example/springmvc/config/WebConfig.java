package com.example.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {
    @Bean
    public MyCustomViewResolver myCustomViewResolver() {
        MyCustomViewResolver resolver = new MyCustomViewResolver();
        resolver.setOrder(0);   // 우선순위 설정 (가장 높은 순위 부여)
        return resolver;
    }
}
