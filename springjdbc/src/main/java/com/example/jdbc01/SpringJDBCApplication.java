package com.example.jdbc01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class SpringJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJDBCApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return args -> {
            // create
            String sql = "insert into users(name, email) values (?, ?)";
            jdbcTemplate.update(sql, "kim", "a@gmail.com");

            // read
            // RowMapper를 이용해야 하는 이유: 결과값을 담을 때 User 클래스의 필드를 직접 매칭
            List<User> users = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
            users.forEach(System.out::println);
        };
    }
}
