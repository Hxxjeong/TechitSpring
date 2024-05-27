package com.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserDAO {
    private EntityManagerFactory entityManagerFactory;

//    public UserDAO() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("UserPU");
//    }
//
//    public void close() {
//        if(entityManagerFactory != null)
//            entityManagerFactory.close();
//    }

    // 유저 생성
    public void createUser(User user) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(user);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // 유저 조회
    public User findUser(Long id) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{
            User user = entityManager.find(User.class, id);
            entityManager.close();
            return user;
        } finally {
            entityManager.close();
        }
    }
}
