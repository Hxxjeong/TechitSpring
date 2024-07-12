package com.example.transactionevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private ItemService itemService;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            itemService.addItem("pen", 1200);
        };
    }
}
