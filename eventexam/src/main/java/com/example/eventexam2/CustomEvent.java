package com.example.eventexam2;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

// 1. 이벤트 정의
@Getter
public class CustomEvent extends ApplicationEvent {
    private String message;

    public CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
