package com.example.oauth2exam.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "social_user")
@Data
public class SocialUser {   // social 정보 저장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_id")
    private String socialId;

    private String provider;

    private String username;

    private String email;

    @Column(name = "avatar_url")
    private String avatarUrl;
}
