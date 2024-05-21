package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {
    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("forwardTest", "hello!!");
        return "forward:/forward";  // forwarding 명시
    }

    @GetMapping("/forward")
    public String forward() {
        return "forwardPage";
    }

    @GetMapping("/redirect")
    public String redirect(Model model) {
        // redirect 되면 return 된 페이지(finalDestination)에서 model 객체를 찾음 -> 페이지에 출력 X
        model.addAttribute("redirectTest", "hello~~");
        return "redirect:/finalDestination";
    }

    @GetMapping("/finalDestination")
    public String finalDestination() {
        return "redirectPage";
    }
}
