package com.example.securityexam2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequest -> authorizeRequest
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(Customizer.withDefaults());

        // 아이디 기억 설정
        http
                .rememberMe(remember -> remember
                        .rememberMeParameter("remember")
                        .tokenValiditySeconds(3000) // 토큰 유지 시간
                );

        return http.build();
    }
}
