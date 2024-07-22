package com.example.securitychat.controller;

import com.example.securitychat.entity.User;
import com.example.securitychat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/userregform")
    public String userregform(){
        return "users/userregform";
    }

    @PostMapping("/userreg")
    public String userreg(@ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "users/userregform";
        }
        User byUsername = userService.findByUsername(user.getUsername());
        if(byUsername != null){
            result.rejectValue("username", null, "이미 사용중인 아이디입니다.");
        }
        userService.registerNewUser(user);
        return "redirect:/welcome";
    }
}