package com.example.springmvc.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    @NotEmpty(message = "username을 입력해주세요.")
    @Size(min = 4, max = 20, message = "username은 4~20자만 가능합니다.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 6, max = 16, message = "비밀번호는 6~16자로 입력해주세요.")
    private String password;
}
