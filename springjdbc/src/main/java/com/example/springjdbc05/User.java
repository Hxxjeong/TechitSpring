package com.example.springjdbc05;

import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String email;

    // 권한 리스트가 있는 경우
//    List<Role> roles = new ArrayList<>();
}
