package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonExamMain {
    public static void find() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try {
            Person person = entityManager.find(Person.class, 1L);
            log.info("Person name: {}", person.getName());
            log.info("Passport number: {}", person.getPassport().getPassportNumber());
        } finally {
            entityManager.close();
        }
    }

    public static void create() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Passport passport = new Passport("1234");
            Person person = new Person("kim");

            passport.setPerson(person);
            person.setPassport(passport);

            entityManager.persist(person);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void update() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Person person = entityManager.find(Person.class, 1L);
            person.setName("choi");

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void delete() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            // person 삭제
//            Person person = entityManager.find(Person.class, 2L);
//            entityManager.remove(person);   // cascade로 passport 같이 삭제

            // passport 삭제
            Passport passport = entityManager.find(Passport.class, 2L);
            if(passport != null)
                passport.getPerson().setPassport(null); // person이 가지고 있는 passport를 null로 만들어야 함
            entityManager.remove(passport);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void main(String[] args) {
//        find();
//        create();
//        update();
        delete();
    }
}
