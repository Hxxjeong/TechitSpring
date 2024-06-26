package com.example.filterexam4.controller;

import com.example.filterexam4.auth.filter.UserContext;
import com.example.filterexam4.entity.User;
import com.example.filterexam4.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 로그인 form
    @GetMapping("/loginform")
    public String loginForm() {
        return "loginform";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        HttpServletResponse response) {
        // 유저 확인
        User findUser = userService.findByUsername(user.getUsername());

        if(findUser != null && user.getPassword().equals(findUser.getPassword())) {
            Cookie cookie = new Cookie("auth", user.getUsername());
            cookie.setPath("/");
            cookie.setHttpOnly(true);   // JS로 쿠키에 접근 불가

            response.addCookie(cookie); // 쿠키는 같은 이름으로 들어오면 업데이트됨
            return "redirect:/welcome";
        }
        else return "redirect:/loginform";
    }

    // 로그인 완료
    @GetMapping("/welcome")
    public String welcome() {
        User user = UserContext.getUser();
        if(user != null) return "welcome";
        else return "redirect:/loginform";
    }

    // 회원 정보
    @GetMapping("/info")
    public String info() {
//        User user = UserContext.getUser();
//        if(user != null) return "info";
//        else return "redirect:/loginform";
        return "info";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // 쿠키 삭제
        Cookie cookie = new Cookie("auth", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);    // 쿠키 유지 시간

        response.addCookie(cookie);

        return "redirect:/loginform";
    }
}
