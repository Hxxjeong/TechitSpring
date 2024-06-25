package com.example.filterexam4.service;

import com.example.filterexam4.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void joinUserTest() {
        User user = new User();
        user.setName("kim");
        user.setUsername("kim");
        user.setPassword("1234");
        user.setEmail("kim@gmail.com");

        User user1 = userService.joinUser(user);

        assertNotNull(user1.getId());
    }
}