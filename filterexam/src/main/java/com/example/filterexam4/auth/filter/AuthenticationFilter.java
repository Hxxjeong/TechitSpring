package com.example.filterexam4.auth.filter;

import com.example.filterexam4.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;    // 형변환
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

            if (auth != null) {
                // ThreadLocal로 전달
                User user = new User();
                user.setUsername(auth);

                UserContext.setUser(user);
            }

            chain.doFilter(servletRequest, servletResponse);
        }
        finally {
            UserContext.clear();
        }
    }
}
