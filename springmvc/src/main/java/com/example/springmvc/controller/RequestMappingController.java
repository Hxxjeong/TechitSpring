package com.example.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestMappingController {
    // RequestMapping의 method는 GetMapping 형태로 쓸 수 있음
    @RequestMapping(value = "/ex", method = RequestMethod.GET)
    @ResponseBody
    public String getExample() {
        return "Get Test";
    }

    // url: localhost:8080/search?query={query} 형태
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<String> Search(@RequestParam String query) {
        ResponseEntity<String> response = new ResponseEntity<>(query, HttpStatus.OK);
        return response;
    }

    @GetMapping("/guest/{name}")
    public String guest(@PathVariable String name) {
        System.out.println(name);
        return "redirect:/result";
    }
}
