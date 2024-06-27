package com.example.projectsecurity.auth.config;

import com.example.projectsecurity.auth.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // csrf 토큰 해제
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/userregform", "/userreg", "/").permitAll()
                        .anyRequest()
                        .authenticated()
                )
//                .formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login")   // 로그인을 진행하는 url
                        .defaultSuccessUrl("/welcome")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                // 동시 로그인 제어
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1) // default: -1 (무제한)
                        .maxSessionsPreventsLogin(true) // default: false - 먼저 로그인 된 브라우저가 expired
                )
                .userDetailsService(customUserDetailsService);

        return http.build();
    }
}
