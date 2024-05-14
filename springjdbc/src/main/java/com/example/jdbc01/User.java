package com.example.jdbc01;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 파라미터를 받는 생성자
public class User {
    private Long id;
    private String name;
    private String email;
}
