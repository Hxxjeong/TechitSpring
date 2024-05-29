package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CompanyExamMain {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEmf().createEntityManager();
        entityManager.getTransaction().begin();

//        Address address1 = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
//        Company company1 = new Company("Company A", address1);
//        entityManager.persist(company1);
//
//        Address address2 = new Address("456 Elm St", "Metropolis", "NY", "10001", "USA");
//        Company company2 = new Company("Company B", address2);
//        entityManager.persist(company2);

        // 데이터 조회
        List<Company> companies = entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
        for (Company company : companies) {
            log.info("Company: " + company.getName() +
                    ", Address: " + company.getAddress().getStreet() +
                    ", " + company.getAddress().getCity() +
                    ", " + company.getAddress().getState() +
                    ", " + company.getAddress().getZipCode() +
                    ", " + company.getAddress().getCountry());
        }

        entityManager.getTransaction().commit();
    }
}
