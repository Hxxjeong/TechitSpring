package com.example.iocexam.controller;

import com.example.iocexam.domain.User;
import com.example.iocexam.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Controller;

@Controller
public class UserContoller {
    private final UserService userService;

    public UserContoller(UserService userService) {
        System.out.println("UserController의 생성자 호출");
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        // 해당 Bean이 생성된 직후 호출
        System.out.println("PostConstruct 실행");
    }
    
    @PreDestroy
    public void destroy() {
        // Bean이 소멸되기 직전 호출
        System.out.println("PreDestroy 실행");
    }
    
    public void joinUser() {
        // 실제 동작할 때는 사용자로부터 정보를 받아옴
        User user = new User();
        user.setName("kim");
        user.setEmail("abc@gmail.com");
        user.setPassword("1234");

        userService.joinUser(user);
    }
}
