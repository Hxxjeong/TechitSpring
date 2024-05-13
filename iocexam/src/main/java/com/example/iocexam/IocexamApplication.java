package com.example.iocexam;

import com.example.iocexam.controller.UserContoller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IocexamApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(IocexamApplication.class, args);
        UserContoller userContoller = context.getBean(UserContoller.class);
        userContoller.joinUser();
    }

}
