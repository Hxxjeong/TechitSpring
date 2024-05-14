package com.example.springdatajdbc01;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, CustomUserRepository {
    List<User> findByName(String name);   // name이 unique 하지 않으면 오류 (List는 오류 x)
}
