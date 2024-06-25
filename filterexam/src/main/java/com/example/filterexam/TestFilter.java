package com.example.filterexam;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@WebFilter(urlPatterns = "/user/*")
public class TestFilter implements Filter {
    public TestFilter() {
        log.info("TestFilter() 실행");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TestFilter init() 실행");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("TestFilter doFilter() 실행 전");
        chain.doFilter(request, response);
        log.info("TestFilter doFilter() 실행 후");
    }

    @Override
    public void destroy() {
        log.info("TestFilter destroy() 실행");
    }
}
