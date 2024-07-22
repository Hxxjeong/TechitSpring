package com.example.githubclientasync.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) // 정의하지 않은 항목 무시
public class RepositoryDto {
    private String name;    // github repository 이름
}
