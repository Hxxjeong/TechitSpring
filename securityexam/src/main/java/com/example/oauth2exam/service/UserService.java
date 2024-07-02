package com.example.oauth2exam.service;

import com.example.oauth2exam.domain.Role;
import com.example.oauth2exam.domain.User;
import com.example.oauth2exam.repository.RoleRepository;
import com.example.oauth2exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;

    // 회원가입
//    @Transactional
//    public User registuser(User user) {
//        // role 추가
//        Role role = roleRepository.findByName("USER");
//        user.setRoles(Collections.singleton(role));
//
//        // 비밀번호 암호화
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        return userRepository.save(user);
//    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // social login 관련
    public Optional<User> findByProviderAndSocialId(String provider, String socialId) {
        return userRepository.findByProviderAndSocialId(provider, socialId);
    }

    @Transactional
    public User saveUser(String username, String name, String email, String socialId, String provider, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setSocialId(socialId);
        user.setProvider(provider);
        user.setPassword(passwordEncoder.encode("")); // 소셜 로그인 사용자의 경우 비밀번호는 비워둠

        return userRepository.save(user);
    }
}
