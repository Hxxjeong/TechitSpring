package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller // view return
public class MyController {
    @GetMapping("/home")
    public String home() {
        return "home";  // view name (RestController: message)
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @ResponseBody
    @GetMapping("/rest")
    public String rest() {
        return "restbody test!";    // data return
    }

    // Model 사용
    @GetMapping("/greeting")
    public String greet(@RequestParam(name="abc", required = false, defaultValue = "kim") String name,
                        @RequestParam(name="age", required = false, defaultValue = "10") int age,
                        Model model) {
        // localhost:8080/greeting?abc=aaa
        System.out.println(name);
        System.out.println(age);

        model.addAttribute("name", name);
        return "greeting";
    }

    // ModelAndView 사용
    @GetMapping("/greeting2")
    public ModelAndView greet2(@RequestParam String name, ModelAndView modelAndView) {
        // localhost:8080/greeting?abc=aaa
        System.out.println(name);

        modelAndView.addObject("name", name);
        modelAndView.setViewName("greeting");
        return modelAndView;
    }
}