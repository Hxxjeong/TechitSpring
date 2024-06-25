package com.example.filterexam;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component  // Bean 주입
@WebFilter(urlPatterns = "/hi") // 어떤 요청에 실행할지 지정
public class FirstFilter implements Filter {
    // 필터 자체를 요청할 수는 X
    // 필터는 어떤 요청을 처리하기 전후에 해야 할 일을 처리
    public FirstFilter() {
        log.info("FirstFilter() 실행");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilter init() 실행");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("FirstFilter doFilter() 실행 전");
        chain.doFilter(request, response);
        log.info("FirstFilter doFilter() 실행 후");
    }

    @Override
    public void destroy() {
        log.info("FirstFilter destroy() 실행");
    }
}
