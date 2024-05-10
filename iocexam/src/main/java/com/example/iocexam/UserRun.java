package com.example.iocexam;

import com.example.iocexam.config.UserConfig;
import com.example.iocexam.controller.UserContoller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserRun {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);

        UserContoller contoller = context.getBean(UserContoller.class);
        contoller.joinUser();
    }
}
