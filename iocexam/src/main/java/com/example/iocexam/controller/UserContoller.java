package com.example.iocexam.controller;

import com.example.iocexam.domain.User;
import com.example.iocexam.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserContoller {
    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
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
