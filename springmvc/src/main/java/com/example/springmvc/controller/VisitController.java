package com.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {
    @GetMapping("/visit")
    public String visit(@CookieValue(name = "lastVisit", defaultValue = "N/A") String lastVisit,
                        HttpServletResponse response, Model model) {
        Cookie cookie = new Cookie("lastVisit", "hey!");
        cookie.setMaxAge(60 * 60); // 1시간 쿠키 유지
        response.addCookie(cookie);

        model.addAttribute("lastVisit", lastVisit); // 쿠키가 있는지 확인 (최초 접속 시 쿠키 X)

        return "visit";
    }
}
