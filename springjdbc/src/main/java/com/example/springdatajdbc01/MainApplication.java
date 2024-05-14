package com.example.springdatajdbc01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return args -> {
//            userRepository.save(new User("choi", "choi@email.com"));

//            User user = userRepository.findById(8L).get();
//            System.out.println(user.getName());
//            List<User> users = userRepository.findByName("kim");
//            users.forEach(u -> System.out.println(u.getName() + ": " + u.getEmail()));

            // 2건씩 페이징 처리하여 0페이지 조회 (페이지 0부터 시작)
            PageRequest pageRequest = PageRequest.of(0, 2);
            Page<User> allUser = userRepository.findAllUsersWithPagination(pageRequest);

            allUser.forEach(user -> System.out.println(user.getName() + ": " + user.getEmail()));
        };
    }
}
