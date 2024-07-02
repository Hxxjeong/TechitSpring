package com.example.oauth2exam.service;

import com.example.oauth2exam.domain.SocialUser;
import com.example.oauth2exam.repository.SocialUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialUserService {
    private final SocialUserRepository socialUserRepository;

    // 소셜에서 정보를 가져와서 저장하거나 이미 있다면 수정
    @Transactional
    public SocialUser createOrUpdate(
            String socialId, String provider, String username, String email, String avatarUrl
    ) {
        Optional<SocialUser> existingUser = socialUserRepository.findBySocialIdAndProvider(socialId, provider);
        SocialUser socialUser;

        // 정보가 이미 있는 경우 업데이트
        if(existingUser.isPresent()) {
            socialUser = existingUser.get();
            socialUser.setUsername(username);
            socialUser.setEmail(email);
            socialUser.setAvatarUrl(avatarUrl);
        }
        else {  // 정보가 없는 경우 생성
            socialUser = new SocialUser();
            socialUser.setSocialId(socialId);
            socialUser.setProvider(provider);
            socialUser.setUsername(username);
            socialUser.setEmail(email);
            socialUser.setAvatarUrl(avatarUrl);
        }

        return socialUserRepository.save(socialUser);
    }
}
