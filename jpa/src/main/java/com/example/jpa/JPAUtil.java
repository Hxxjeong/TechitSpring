package com.example.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private JPAUtil() {}

    // EntityManager를 Singleton으로 만들기 위함
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("UserPU");

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    // 어플리케이션이 종료될 때 close() 호출
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(emf != null)
                System.out.println("--------- emf close ---------");
                emf.close();
        }));
    }
}
