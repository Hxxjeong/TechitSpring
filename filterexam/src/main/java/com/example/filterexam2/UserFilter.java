package com.example.filterexam2;

import jakarta.servlet.*;

import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 사용자가 요청하면서 보낸 값이 있다면 추출해서 UserContext에 저장
        try {
            User user = new User();
            user.setUsername("kim");
            user.setPassword("1234");

            UserContext.setUser(user);

            chain.doFilter(request, response);
        }
        finally {
            UserContext.clear();    // 기존에 사용했던 스레드를 또 사용할 수 있기 때문에 값을 삭제하고 재사용
        }
    }
}
