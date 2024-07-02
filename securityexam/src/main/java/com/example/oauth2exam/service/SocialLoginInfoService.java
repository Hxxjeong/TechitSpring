package com.example.oauth2exam.service;

import com.example.oauth2exam.domain.SocialLoginInfo;
import com.example.oauth2exam.repository.SocialLoginInfoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialLoginInfoService {
    private final SocialLoginInfoRepository socialLoginInfoRepository;

    @Transactional
    public SocialLoginInfo create(String provider, String socialId) {
        SocialLoginInfo socialLoginInfo = new SocialLoginInfo();
        socialLoginInfo.setProvider(provider);
        socialLoginInfo.setSocialId(socialId);

        return socialLoginInfoRepository.save(socialLoginInfo);
    }

    public Optional<SocialLoginInfo> findByProviderAndUuidAndSocialId(
            String provider, String uuid, String socialId
    ) {
        return socialLoginInfoRepository.findByProviderAndUuidAndSocialId(provider, uuid, socialId);
    }
}
