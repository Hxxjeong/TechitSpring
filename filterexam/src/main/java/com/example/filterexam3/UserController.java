package com.example.filterexam3;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {
    @GetMapping("/loginform")
    public String loginform(Model model) {
        model.addAttribute("user", new User());
        return "loginform";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        HttpServletResponse response) {
        // 사용자가 보낸 username과 비밀번호가 일치하는지 확인
        log.info("username: {}, password: {}", user.getUsername(), user.getPassword());

        // 실제 서비스에서는 아이디와 비밀번호 검사
        if(user.getUsername().equals("kim") && user.getPassword().equals("1234")) {
            Cookie cookie = new Cookie("auth", "user");
            cookie.setPath("/");    // 어느 url에서 유효한지

            // 클라이언트에게 쿠키 전달
            response.addCookie(cookie);
            log.info(String.valueOf(cookie.getValue()));
        }
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        User user = UserContext.getUser();
        if(user != null) return "welcome";
        else return "redirect:/loginform";
    }

    @GetMapping("/info")
    public String info(HttpServletRequest request) {
        // 로그인 한 사용자 확인

        // 1. 쿠키로 직접 확인
//        String auth = null;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null) {
//            for(Cookie cookie: cookies) {
//                if (cookie.getName().equals("auth")) {
//                    auth = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        if(auth != null) return "info";
//        return "redirect:/loginform";

        // 2. userContext로 확인
        User user = UserContext.getUser();
        if(user != null) return "info";
        else return "redirect:/loginform";
    }
}
