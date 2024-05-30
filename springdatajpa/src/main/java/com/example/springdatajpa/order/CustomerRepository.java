package com.example.springdatajpa.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 이름으로 조회
    List<Customer> findByName(String name);

    // 이메일로 조회
    List<Customer> findByEmail(String email);

    // 이메일에 특정 문자열을 포함하고 있는 고객 조회
    List<Customer> findByEmailLike(String pattern);

    // 각 고객과 고객의 주문 수 조회
    @Query("select c, count(o) from Customer c left join c.orders o group by c")
    List<Object[]> findOrderByCustomer();
}
