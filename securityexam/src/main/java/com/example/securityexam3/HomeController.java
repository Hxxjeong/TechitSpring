package com.example.securityexam3;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm Page";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }

    @GetMapping("/test")
    public String test() {
        // @AuthenticationPrincipal로 표현 가능
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.isAuthenticated());
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String)
            return "익명 사용자입니다.";
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return "username: " + userDetails.getUsername();
    }
}
