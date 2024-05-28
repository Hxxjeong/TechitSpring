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

    // 유저 수정
    public void updateUser(User user) { // parameter의 user는 영속성 컨텍스트 X
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // parameter의 user를 존재한 영속성 컨텍스트와 merge
            entityManager.merge(user);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    // 유저 삭제
    public void deleteUser(User user) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // 영속성 컨텍스트에 연결되어 있는 항목 삭제
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
