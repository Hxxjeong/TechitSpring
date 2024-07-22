package com.example.completablefuture;

import com.example.completablefuture.service.AsyncService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@SpringBootApplication
@EnableAsync
public class CompletableFutureApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompletableFutureApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(AsyncService asyncService) {
        return args -> {
            System.out.println("async 메소드 호출");
            CompletableFuture<String> result1 = asyncService.asyncMethod();
            CompletableFuture<String> result2 = asyncService.asyncMethod2();

            // 비동기 메소드 호출 후 다른 작업 수행
            System.out.println("another task");

            // 비동기 메소드 결과
            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(result1, result2)
                    .thenRun(() -> {
                        System.out.println("asyncMethod result: " + result1.join());
                        System.out.println("asyncMethod2 result: " + result2.join());
                    });

            // 모든 작업이 완료될 때까지 기다림
            combinedFuture.join();
        };
    }
}
