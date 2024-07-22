package com.example.githubclientasync.controller;

import com.example.githubclientasync.dto.RepositoryDto;
import com.example.githubclientasync.dto.UserDto;
import com.example.githubclientasync.service.GithubApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class GithubController {
    private final GithubApiService githubApiService;

    @GetMapping("/github/{username}/all")
    public CompletableFuture<String> getUserDetails(@PathVariable String username) {
        CompletableFuture<List<RepositoryDto>> reposFuture = githubApiService.getUserRepos(username);
        CompletableFuture<List<UserDto>> followersFuture = githubApiService.getUserFollowers(username);
        CompletableFuture<List<UserDto>> followingFuture = githubApiService.getUserFollowing(username);

        System.out.println("ready!");

        return CompletableFuture.allOf(reposFuture, followersFuture, followingFuture)
                .thenApply(v -> {
                    List<RepositoryDto> repos = reposFuture.join();
                    List<UserDto> follwers = followersFuture.join();
                    List<UserDto> following = followingFuture.join();

                    return formatResult(repos, follwers.size(), following.size());
                });
    }

    private String formatResult(List<RepositoryDto> repos, int followersCount, int followingCount) {
        StringBuilder result = new StringBuilder();
        result.append("<ul>");
        for (RepositoryDto repo : repos) {
            result.append("<li>");
            result.append("<strong>").append(repo.getName()).append("</strong><br>");
            result.append("Followers: ").append(followersCount).append("<br>");
            result.append("Following: ").append(followingCount).append("<br>");
            result.append("</li>");
        }
        result.append("</ul>");
        return result.toString();
    }
}
