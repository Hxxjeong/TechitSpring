package com.example.githubclient.controller;

import com.example.githubclient.service.GithubApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubApiController {
    private final GithubApiService githubApiService;

    @GetMapping("/github/{username}/repos")
    public String getUserRepos(@PathVariable String username) {
        return githubApiService.getUserRepos(username);
    }
}
