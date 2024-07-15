package com.example.redischat.service;

import com.example.redischat.entity.Role;
import com.example.redischat.repository.RoleRepository;
import com.example.redischat.entity.User;
import com.example.redischat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public User registerNewUser(User user) {
        Role userRole = roleRepository.findByName("USER");
        user.setRoles(Collections.singleton(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}