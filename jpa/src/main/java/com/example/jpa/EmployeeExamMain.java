package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeExamMain {
    private static void find() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        try{
            Project project = entityManager.find(Project.class, 1L);
            log.info("Project name: {}", project.getName());

            for (Employee employee : project.getEmployees()) {
                log.info("Employee name: {}", employee.getName());
            }
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    private static void create() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            Employee employee = new Employee();
            employee.setName("kim");

            Project project = new Project();
            project.setName("project1");

            employee.getProjects().add(project);
            project.getEmployees().add(employee);

            entityManager.persist(employee);
            entityManager.persist(project);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    private static void update() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            Employee employee = entityManager.find(Employee.class, 3L);
            employee.setName("park");

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    private static void delete() {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();
        try{
//            Employee employee = entityManager.find(Employee.class, 3L);
//            entityManager.remove(employee); // 직원이 삭제될 때 프로젝트와의 관계도 삭제됨

            // 1번 사원이 2번 플젝에서 빠지는 경우
            Employee employee = entityManager.find(Employee.class, 1L);
            employee.getProjects().remove(entityManager.find(Project.class, 2L));

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
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
