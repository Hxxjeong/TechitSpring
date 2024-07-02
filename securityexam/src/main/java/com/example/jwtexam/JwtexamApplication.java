package com.example.jwtexam;

import com.example.jwtexam.auth.jwt.util.JwtTokenizer;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class JwtexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtexamApplication.class, args);
    }

    @Autowired
    JwtTokenizer jwtTokenizer;

//    @Bean
    public CommandLineRunner run() {
        return args -> {
            String accessToken = jwtTokenizer.createAccessToken(1L, "test@test.com", "test", "test", Arrays.asList("ROLE_USER"));
            log.info("access token: {}", accessToken);

            String refreshToken = jwtTokenizer.createRefreshToken(1L, "test@test.com", "test", "test", Arrays.asList("ROLE_USER"));
            log.info("refresh token: {}", refreshToken);

//            Long id = jwtTokenizer.getUserIdFromToken(accessToken);
//            log.info("id: {}", id);

            Claims claims = jwtTokenizer.parseAccessToken(accessToken);
            log.info("access token claims: {}", claims);

//            Claims refreshTokenClaim = jwtTokenizer.parseRefreshToken(refreshToken);
//            log.info("refresh token claims: {}", refreshTokenClaim);
        };
    }
}
