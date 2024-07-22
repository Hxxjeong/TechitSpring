package com.example.githubclientasync.service;

import com.example.githubclientasync.dto.RepositoryDto;
import com.example.githubclientasync.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GithubApiService {
    @Value("${github.api.url}")
    private String githubApiUrl;

    @Value("${github.api.token}")
    private String githubApiToken;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GithubApiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Async("taskExecutor")
    public CompletableFuture<List<RepositoryDto>> getUserRepos(String username) {
        System.out.println("getUserRepos() ");
        return CompletableFuture.supplyAsync(() -> {
            String url = githubApiUrl + "/users/" + username + "/repos";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Bearer " + githubApiToken);

            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            try {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<RepositoryDto>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Async("taskExecutor")
    public CompletableFuture<List<UserDto>> getUserFollowing(String username) {
        System.out.println("getUserFollowing() ");
        return CompletableFuture.supplyAsync(() -> {
            String url = githubApiUrl + "/users/" + username + "/following";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + githubApiToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            try {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<UserDto>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Async("taskExecutor")
    public CompletableFuture<List<UserDto>> getUserFollowers(String username) {
        System.out.println("getUserFollowers() ");
        return CompletableFuture.supplyAsync(() -> {
            String url = githubApiUrl + "/users/" + username + "/followers";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + githubApiToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            try {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<UserDto>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
