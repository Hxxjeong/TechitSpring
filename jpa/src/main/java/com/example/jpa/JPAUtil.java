package com.example.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private JPAUtil() {}

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("UserPU");

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(emf != null)
                System.out.println("--------- emf close ---------");
                emf.close();
        }));
    }
}
