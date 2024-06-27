package com.example.securityexam3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequest -> authorizeRequest
                        // 권한을 부여할 때는 차례로 동작 (requestMatchers) → 구체적인 정보가 먼저 와야 함
                        .requestMatchers("/shop/**", "/test").permitAll()   // 지정한 페이지 누구나 접근 가능
                        .requestMatchers("/user/mypage").hasRole("USER")    // 특정 권한을 가진 사용자만 접근 가능
                        .requestMatchers("/admin/abc").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERUSER")  // 여러 권한에 접근 허용
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 실제 프로젝트에서는 DB에 있는 사용자 정보 이용

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("1234")) // 반드시 비밀번호를 암호화해야 함
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        UserDetails superuser = User.withUsername("superuser")
                .password(passwordEncoder().encode("1234"))
                .roles("SUPERUSER")
                .build();

        return new InMemoryUserDetailsManager(user, admin, superuser);
    }
}
