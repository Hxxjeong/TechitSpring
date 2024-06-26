package com.example.securityexam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // Security 설정
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequest -> authorizeRequest
                        .anyRequest()   // 모든 요청
                        .authenticated()    // 인증 요구
                );
//                .formLogin(Customizer.withDefaults());   // 인증 방식 (기본 form)

        // 로그인 form 설정
        http
                .formLogin(formLogin -> formLogin
//                .loginPage("/loginForm")    // 지정한 로그인 페이지
                .defaultSuccessUrl("/suceess")
                .failureUrl("/fail")
                .successHandler((request, response, authentication) -> {
                    log.info("authentication: {}", authentication.getName());   // 로그인 성공 시 defaultSuccessUrl은 무시됨
                    response.sendRedirect("/success");
                })
                .failureHandler((request, response, exception) -> {
                    log.info("exception: {}", exception.getMessage());
                    response.sendRedirect("/login");
                })
//                .usernameParameter("userId")
//                .passwordParameter("passwd")
//                .loginProcessingUrl("/login_p")
                .permitAll()    // 로그인 페이지(loginPage)에 대한 요청 모두 허용
        );

        // 로그아웃 설정
        // 기본 설정: 세션 무효화, 인증 토큰 삭제, 쿠키 삭제, 로그인 페이지로 redirect
        http.logout(logout -> logout
//                .logoutUrl("/log_out")
                .logoutSuccessUrl("/login")
                .addLogoutHandler((request, response, authentication) -> {
                    log.info("addLogoutHandler ");
                    HttpSession session = request.getSession();
                    session.invalidate();   // 세션 무효화
                })
                .logoutSuccessHandler((request, response, authentication) -> {  // logoutSuccessUrl은 무시됨
                    log.info("logoutSuccessHandler");
                    response.sendRedirect("/login");
                })
                .deleteCookies("remember-me")
        );

        return http.build();
    }
}
