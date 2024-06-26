package com.example.filterexam4.auth.filter;

import com.example.filterexam4.entity.User;
import com.example.filterexam4.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;

import java.io.IOException;

@Setter // setter injection을 위한 어노테이션
public class AuthenticationFilter implements Filter {
    private UserService userService;    // 서비스 사용

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;    // 형변환
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String path = request.getRequestURI();    // path (/admin, /welcome)

            String auth = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("auth")) {
                        auth = cookie.getValue();
                        break;
                    }
                }
            }

            // 인증된 사용자
            if (auth != null) {
                // path에 따라 사용자 권한별로 접근
                User user = userService.findByUsername(auth);

                if(user != null) {
                    user.setUsername(auth);
                    UserContext.setUser(user);

                    if(path.equals("/admin") &&
                            user.getRoles().stream()
                                    .anyMatch(role -> role.getName().equals("ROLE_ADMIN"))
                            ||
                       path.equals("/info") &&
                               user.getRoles().stream()
                                       .anyMatch(role -> role.getName().equals("ROLE_USER"))) {
                        chain.doFilter(servletRequest, servletResponse);
                        UserContext.clear();
                        return;
                    }
                }

                if(path.equals("/admin") || path.equals("/info")) { // 권한이 없는 사용자
                    response.sendRedirect("/access-denied");    // redirect할 페이지
                }

                // ThreadLocal로 전달
//                User user = new User();
//                user.setUsername(auth);
//
//                UserContext.setUser(user);
            }

            chain.doFilter(servletRequest, servletResponse);
        }
        finally {
            UserContext.clear();
        }
    }
}
