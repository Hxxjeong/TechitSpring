package com.example.completablefuture.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncService {
    @Async
    public CompletableFuture<String> asyncMethod() throws InterruptedException {
        System.out.println("asyncMethod() start");
        // 작업 진행
        Thread.sleep(2000);
        System.out.println("asyncMethod() end");
        return CompletableFuture.completedFuture("asyncMethod() result");
    }

    @Async
    public CompletableFuture<String> asyncMethod2() throws InterruptedException {
        System.out.println("asyncMethod2() start");
        // 작업 진행
        Thread.sleep(3000);
        System.out.println("asyncMethod2() end");
        return CompletableFuture.completedFuture("asyncMethod2() result");
    }
}
