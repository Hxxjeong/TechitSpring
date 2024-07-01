package com.example.jwtexam.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor // Builder를 쓸 때 사용
public class UserLoginDto {
    @NotEmpty
    private String username;

    @NotEmpty
//    @Pattern(regexp=  "^(?=.[a-zA-Z])(?=.\\d)(?=.*\\W).{8,20}$")    // 비밀번호 패턴 설정
    private String password;
}
