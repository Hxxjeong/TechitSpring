package com.example.iocexam.dao;

import com.example.iocexam.domain.User;

import java.util.List;

public interface UserDao {
    public User getUser(String email);
    public List<User> getUsers();
    public void createUser(User user);
}
