package com.example.springdatajdbc01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// Entity (데이터베이스와의 관계를 알려주는 역할)
// DTO는 단순히 값만 담아서 전달
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table("users") // 테이블명이 db와 다를 때 사용
public class User {
    @Id
    private Long id;    // primary key
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
