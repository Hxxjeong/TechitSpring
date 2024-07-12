package com.example.transactionevent;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ItemRegEvent extends ApplicationEvent {
    private final String name;
    private final int price;

    public ItemRegEvent(Object source, String name, int price) {
        super(source);
        this.name = name;
        this.price = price;
    }
}
