package com.example.springjdbc02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbc02Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbc02Application.class, args);
    }

    @Bean
    public CommandLineRunner transactionDemo(UserDao userDao) {
        return args -> {
            try {
                userDao.createAndUpdateUser("kwon", "kwon@email.com", "asdf@email.com");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
