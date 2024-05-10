package com.example.iocexam.service;

import com.example.iocexam.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public void joinUser(User user);    // 회원가입
}
