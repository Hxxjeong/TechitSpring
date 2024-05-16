package com.example.springjdbc07;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringJdbcApplication07 {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication07.class, args);
    }

    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return args -> {
            String sql = """
                    select *
                    from users
                    where email like ?;
                    """;
            jdbcTemplate.query(sql, rs -> {
                while(rs.next()) {
                    System.out.println("User: " + rs.getString("name") + " - " + rs.getString("email"));
                }
            }, "%email.com");
        };
    }
}
