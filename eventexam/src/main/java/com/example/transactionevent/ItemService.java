package com.example.transactionevent;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void addItem(String name, int price) {
        //  아이템 저장
        Item item = new Item(name, price);
        itemRepository.save(item);

        // 이벤트 등록
//        ItemRegEvent itemRegEvent = new ItemRegEvent(this, name, price);
//        applicationEventPublisher.publishEvent(itemRegEvent);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() { // commit이 될 때만 이벤트 발생
                ItemRegEvent itemRegEvent = new ItemRegEvent(this, name, price);
                applicationEventPublisher.publishEvent(itemRegEvent);
            }
        });

//        if(1==1) throw new RuntimeException("강제 예외!!");
    }
}
