package com.example.projectsecurity.service;

import com.example.projectsecurity.domain.Role;
import com.example.projectsecurity.domain.User;
import com.example.projectsecurity.repository.RoleRepository;
import com.example.projectsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public User registuser(User user) {
        // role 추가
        Role role = roleRepository.findByName("USER");
        user.setRoles(Collections.singleton(role));

        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
