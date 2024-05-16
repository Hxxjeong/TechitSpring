package com.example.springjdbc02;

import org.springframework.transaction.annotation.Transactional;

public interface UserDao {
    @Transactional  // 트랜잭션 처리
    void createAndUpdateUser(String name, String email, String newEmail);
}
