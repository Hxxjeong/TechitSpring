package com.example.springjdbc08;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbcApplication08 {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication08.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserDao userDao) {
        return args -> {
            User user = new User();
            user.setName("han");
            user.setEmail("han@email.com");

            User resultUser = userDao.createUser(user);
            System.out.println(resultUser.getId());
        };
    }
}
