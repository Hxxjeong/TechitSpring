package com.example.aopexam;

import org.springframework.stereotype.Service;

// Target
@Service
public class SimpleService {
    // Business Method
    public String doSomething() {
        // 핵심 관심
        System.out.println("SimpleService doSomething run");
        return "Doing something";
    }

    public void hello() {
        System.out.println("hello~");
//        if(true) throw new RuntimeException();
    }

    public void setName(String name) {
        System.out.println("setName() method run");
    }
}
