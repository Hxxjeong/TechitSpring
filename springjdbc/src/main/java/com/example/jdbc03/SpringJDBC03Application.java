package com.example.jdbc03;

import com.example.jdbc03.dao.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJDBC03Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringJDBC03Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserDao userDao) {
        return args -> {
            // insert
//            userDao.createUser(new User(null, "kim", "test@gmail.com"));

//            // update (예외 발생)
//            userDao.updateUserEmail("lee", "kkk@naver.com");
//
//            //delete (예외 발생)
//            userDao.deleteUser("park");

            // select
            userDao.findAllUsers().forEach(user -> System.out.println(user.getName() + ": " + user.getEmail()));
        };
    }
}
