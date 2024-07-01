package com.example.jwtexam.service;

import com.example.jwtexam.domain.RefreshToken;
import com.example.jwtexam.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    // 토큰 저장
    @Transactional
    public RefreshToken create(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken);
    }

    // 토큰 삭제
    public void delete(String refreshToken) {
        // value로 찾고 있다면 삭제
        refreshTokenRepository.findByValue(refreshToken)
                .ifPresent(refreshTokenRepository::delete);
    }
}
