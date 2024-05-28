package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolExamMain {
    public static void find() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();

        try{
//            School school = entityManager.find(School.class, 1L);
//            log.info("School Name: {}", school.getName());
//
//            // 학교 id가 1인 학생의 이름
//            for (Student student : school.getStudents()) {
//                log.info("Student name: {}", student.getName());
//            }

            // id가 5인 학생의 이름과 학교 이름
            Student student = entityManager.find(Student.class, 5L);
            log.info("Student name: {}", student.getName());
            log.info("School name: {}", student.getSchool().getName());
        } finally {
            entityManager.close();
        }
    }

    public static void create() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            School school = new School("Like School");
            Student student1 = new Student("kim", school);
            Student student2 = new Student("lee", school);
            Student student3 = new Student("park", school);

            // 학생 추가
            school.getStudents().add(student1);
            school.getStudents().add(student2);
            school.getStudents().add(student3);

            entityManager.persist(school);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void update() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            // id가 2인 학교
            School school = entityManager.find(School.class, 2L);
            school.setName("Update School");

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void delete() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            // id가 4인 학교
            School school = entityManager.find(School.class, 4L);
            entityManager.remove(school);

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
