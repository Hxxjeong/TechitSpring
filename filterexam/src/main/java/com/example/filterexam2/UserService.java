package com.example.filterexam2;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void list() {
        User user = UserContext.getUser();

        // user의 유무에 따라 비즈니스 처리
        System.out.println(user);
    }
}
