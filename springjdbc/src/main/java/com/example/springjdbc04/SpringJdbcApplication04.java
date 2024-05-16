package com.example.springjdbc04;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringJdbcApplication04 {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication04.class, args);
    }

    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return args -> {
            String sql = "select * from users";
            List<User> users = jdbcTemplate.query(sql, new UserRowMapper());
            users.forEach(System.out::println);
        };
    }

    // RowMapper: 결과 집합의 각 행을 개별 객체로 매핑하는 데 사용
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            return new User(id, name, email);
        }
    }

    @AllArgsConstructor
    static class User {
        private Long id;
        private String name;
        private String email;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
