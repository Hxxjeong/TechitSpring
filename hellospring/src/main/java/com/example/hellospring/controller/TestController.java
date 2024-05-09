package com.example.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/morning")
    public String test1() {
        return "morning";
    }

    @GetMapping("/lunch")
    public String test2() {
        return "lunch";
    }

    @GetMapping("/dinner")
    public String test3() {
        return "dinner";
    }
}
