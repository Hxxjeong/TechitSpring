package com.example.jdbc02.dao;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    List<User> findAllUsers();
    void updateUserEmail(String name, String email);    // email update
    void deleteUser(String name);   // 이름을 기준으로 삭제
}
