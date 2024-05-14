package com.example.jdbc02;

import com.example.jdbc02.dao.User;
import com.example.jdbc02.dao.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJDBC02Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringJDBC02Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserDao userDao) {
        return args -> {
            // insert
//            userDao.createUser(new User(null, "kim", "test@gmail.com"));

            // select
            userDao.findAllUsers().forEach(user -> System.out.println(user.getName() + ": " + user.getEmail()));

//            // update
//            userDao.updateUserEmail("kim", "kkk@naver.com");
//
//            //delete
//            userDao.deleteUser("park");
        };
    }
}
