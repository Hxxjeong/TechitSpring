package com.example.projectsecurity.controller;

import com.example.projectsecurity.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.projectsecurity.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/userregform")
    public String regForm() {
        return "users/userregform";
    }

    @PostMapping("/userreg")
    public String userReg(@ModelAttribute("user") User user, BindingResult result) {
        if(result.hasErrors()) {
            return "users/userregform";
        }

        // 유저가 이미 존재하는 경우
        User byUsername = userService.findByUsername(user.getUsername());
        if(byUsername != null) {
            result.rejectValue("username", null, "이미 존재하는 ID입니다.");
            return "users/userregerror";
        }

        userService.registuser(user);   // 회원가입 완료
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "users/welcome";
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "users/loginform";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
