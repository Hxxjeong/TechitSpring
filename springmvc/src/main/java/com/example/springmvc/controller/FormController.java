package com.example.springmvc.controller;

import com.example.springmvc.domain.User2;
import com.example.springmvc.domain.UserForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "form";
    }

    // @RequestParam으로 하나씩 넘기기
//    @PostMapping("/submitForm")
//    public String submit(@RequestParam String username, @RequestParam String password) {
//        System.out.println(username + " :: " + password);
//        return "result";
//    }

    // 넘길 값이 많을 때 @ModelAttribute 유용
    // 전송된 form 데이터를 userForm 클래스 인스턴스에 바인딩
    @PostMapping("/submitForm")
    public String submit(@Valid @ModelAttribute("userForm") UserForm userForm,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) { // 유효성 검사에서 에러일 때
            return "form";
        }
        return "result";
    }
}
