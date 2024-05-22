package com.example.springmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("visitCount2")   // annotation 이용
public class SessionController {
    // HttpSession 직접 이용
//    @GetMapping("/visit2")
//    public String trackVisit(HttpSession session, Model model) {
//        // 세션으로부터 방문 횟수 얻기
//        Integer visitCount = (Integer) session.getAttribute("visitCount");
//
//        // model에 방문 횟수를 넣으면 새로고침 해도 방문 횟수가 늘어나지 않음 (request scope)
////        Integer visitCount1 = (Integer) model.getAttribute("visitCount");
//
//        if(visitCount == null) {    // 한번도 방문하지 않은 경우
//            visitCount = 0; // 초기값 설정
//        }
//        visitCount++;
//        session.setAttribute("visitCount", visitCount);
//
//        // model 객체에 넣을 떄
//        model.addAttribute("visitCount", visitCount);
//
//        return "visit2";
//    }

    // 방문 횟수 초기화
    @ModelAttribute("visitCount2")
    public Integer initVisitCount() {
        return 0;
    }

    // 어노테이션 이용
    @GetMapping("/visit2")
    public String trackVisit(@ModelAttribute("visitCount2") Integer visitCount2, Model model) {
        visitCount2++;
        model.addAttribute("visitCount2", visitCount2);
        return "visit2";
    }

    // 세션 삭제
    @GetMapping("resetVisit")
    public String resetVisit(SessionStatus status) {
        status.setComplete();   // 사용자 세션 모두 초기화
        return "redirect:/visit2";
    }

    // 특정 세션 삭제 (HttpSession 사용했을 때 가능)
    @GetMapping("resetVisit2")
    public String resetVisit2(HttpSession session) {
        // session 객체에서 해당 속성만 초기화
        session.removeAttribute("visitCount");
//        session.setAttribute("visitCount", 0);

//        session.invalidate(); // 세션 전체 무효화
        return "redirect:/visit2";
    }
}
