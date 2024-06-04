package com.example.restexam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {
    @GetMapping("/api/error")
    public String apiError() {
        throw new RuntimeException("API Error");
    }
}
