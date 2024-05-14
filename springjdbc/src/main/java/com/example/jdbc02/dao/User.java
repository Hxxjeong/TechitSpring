package com.example.jdbc02.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    private Long id;
    private String name;
    private String email;
}
