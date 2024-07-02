package com.example.oauth2exam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "social_login_info")
@Getter
@Setter
// 회원가입을 위한 객체
// 소셜 로그인 후 20분 안에 회원가입을 하지 않으면 오류 처리를 하기 위한 엔티티
public class SocialLoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String uuid;

    public SocialLoginInfo() {
        // 로그인한 시간, UUID 값 생성
        // 소셜 로그인 이후에 특정한 시간까지만 추가 작업 가능
        this.createdAt = LocalDateTime.now();
        this.uuid = UUID.randomUUID().toString();
    }
}
