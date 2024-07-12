package com.example.transactionevent;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ItemRegEventListener {
    @TransactionalEventListener
    public void handleItemRegEvent(ItemRegEvent event) {
        // 이벤트가 발생했을 때 할 일
        // ex. 상품이 등록되었을 때 고객에게 이메일 전송
        System.out.println("아이템 등록 이벤트 처리 :: " + event.getName() + " 상품 가격 :: " + event.getPrice());
    }
}
