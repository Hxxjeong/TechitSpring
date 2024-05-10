package com.example.iocexam.dao;

import com.example.iocexam.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {
        System.out.println(user + " 의 정보가 저장되었습니다.");
    }
}
