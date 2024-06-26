package com.example.filterexam4.service;

import com.example.filterexam4.entity.Role;
import com.example.filterexam4.entity.User;
import com.example.filterexam4.repository.RoleRepository;
import com.example.filterexam4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // 일반회원 가입
    @Transactional
    public User joinUser(User user) {
        // Role 정보 생성
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    // 회원 정보 조회
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
