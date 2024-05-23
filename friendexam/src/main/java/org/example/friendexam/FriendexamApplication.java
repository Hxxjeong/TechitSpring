package org.example.friendexam;

import org.example.friendexam.repository.FriendRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FriendexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendexamApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(FriendRepository friendRepository) {
//        return args -> {
//            friendRepository.findAll().forEach(System.out::println);
//        };
//    }
}
