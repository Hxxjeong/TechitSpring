package com.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserRun {
    public static void main(String[] args) {
        // Factory 객체를 통해 EntityManager 객체를 만들 수 있음
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("UserPU");
        EntityManager entityManager = emf.createEntityManager();

        // entity 생성 (영속성 컨텍스트 관리 X)
        User user = new User("kim", "kim@gmail.com");
//        user.setId(1L);
//        user.setName("kim");
//        user.setEmail("kim@gmail.com");

        // 영속성 컨텍스트에게 관리 맡기기
        entityManager.getTransaction().begin(); // transaction 시작
        entityManager.persist(user); // 영속 상태
        entityManager.getTransaction().commit();    // DB에 저장

        entityManager.close();
        emf.close();
    }
}
