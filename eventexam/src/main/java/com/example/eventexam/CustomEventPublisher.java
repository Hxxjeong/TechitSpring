package com.example.eventexam;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// 3. 이벤트 등록
@Component
@RequiredArgsConstructor
public class CustomEventPublisher {
    // post-comment 예제일 경우 CommentService가 됨
    private final ApplicationEventPublisher applicationEventPublisher;

    // 댓글 등록
    public void publisherEvent(final String message) {
        // 댓글 등록 로직

        // 이벤트 실행
        CustomEvent customEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customEvent);
    }
}
