package com.example.springdatajpa.order;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void save() {
        Customer customer = new Customer("kim", "kim@example.com", 20);
        customerRepository.save(customer);
        log.info("Customer Name: {}", customer.getName());
    }

    @Test
    void delete() {
        customerRepository.deleteById(5L);
        assertThat(customerRepository.findById(5L)).isNotPresent();
    }

    @Test
    void findByName() {
        List<Customer> customer = customerRepository.findByName("김철수");
        customer.forEach(c -> log.info("customer name: {}", c.getName()));
    }

    @Test
    void findByEmail() {
        List<Customer> customer = customerRepository.findByEmail("choi@example.com");
        customer.forEach(c -> log.info("customer email: {}", c.getEmail()));
    }

    @Test
    void findByEmailLike() {
        List<Customer> customer = customerRepository.findByEmailLike("%k%");
        customer.forEach(c -> log.info("customer email: {}", c.getEmail()));

        // Given
        Customer customer1 = new Customer("user1", "user1@test.com");
        customerRepository.save(customer1);

        // When
        List<Customer> customers = customerRepository.findByEmailLike("%test%");

        // Then
        assertThat(customers).hasSize(1);
    }

    @Test
    void customerOrder() {
        List<Object[]> orders = customerRepository.findOrderByCustomer();
        orders.forEach(result -> {
            Customer customer = (Customer) result[0];
            Long count = (Long) result[1];
            log.info("고객 이름: {}, 주문 수: {}", customer.getName(), count);
        });
    }
}