package com.example.oauth2exam.auth.security;

import com.example.oauth2exam.domain.Role;
import com.example.oauth2exam.domain.User;
import com.example.oauth2exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.roles(  // role 얻기
                user.getRoles().stream()
                        .map(Role::getName)
                        .toArray(String[]::new)
        );

        return userBuilder.build();
    }
}
