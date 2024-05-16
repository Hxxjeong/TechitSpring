package com.example.springjdbc03;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbcApplication03 {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication03.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return args -> {
            try {
                userService.executeComplexBusinessProcess("Kade", "error@email.com");
            }
            catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
