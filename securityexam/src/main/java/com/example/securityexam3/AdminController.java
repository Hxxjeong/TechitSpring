package com.example.securityexam3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    // admin만 접근
    @GetMapping("/abc")
    public String abc() {
        return "abc";
    }

    // admin과 superuser 접근
    @GetMapping("/def")
    public String def() {
        return "def";
    }
}
