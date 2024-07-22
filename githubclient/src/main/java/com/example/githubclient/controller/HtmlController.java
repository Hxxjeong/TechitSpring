package com.example.githubclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/gitrepos")
    public String gitRepos() {
        return "repos";
    }
}
