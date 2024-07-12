package com.example.eventexam2;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 2. 이벤트가 발생했을 때 해야 할 일 구현
@Component  // Bean 등록
public class CustomEventListener {

    @EventListener
    @Async
    public void handleCustomEvent(CustomEvent event) {
        System.out.println("CustomEventListener Thread name :: " + Thread.currentThread().getName());
        System.out.println("event 발생! " + event.getMessage());

        // ex. 댓글 개수 1개 증가 (이벤트가 발생했을 때 해야 할 일)
    }
}
