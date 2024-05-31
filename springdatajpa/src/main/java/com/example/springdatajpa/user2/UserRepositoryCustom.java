package com.example.springdatajpa.user2;
import java.util.List;

public interface UserRepositoryCustom {
    List<User> findUsersByName(String name);
    List<User> findUsersDynamically(String name, String email);
}
