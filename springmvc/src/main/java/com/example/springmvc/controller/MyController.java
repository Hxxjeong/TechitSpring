package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller // view return
public class MyController {
    @GetMapping("/home")
    public String home() {
        return "home";  // view name (RestController: message)
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("contact")
    public String contact() {
        return "contact";
    }
}