package com.example.springdatajpa.user2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.MessageFormat;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return args -> {
            // Criteria API를 사용한 사용자 조회 예제
            List<User> usersByNameCriteria = userRepository.findUsersByName("lee");
            usersByNameCriteria.forEach(user -> log.info("Criteria API로 찾은 사용자: {}, Email: {}", user.getName(), user.getEmail()));

            List<User> list = userRepository.findUsersDynamically(null, "choi@gmail.com");
            list.forEach(user -> log.info("Criteria API로 동적으로 찾은 사용자: {}, 이메일: {}", user.getName(), user.getEmail()));

        };
    }
}