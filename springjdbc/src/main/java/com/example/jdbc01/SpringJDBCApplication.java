package com.example.jdbc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

@SpringBootApplication
public class SpringJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJDBCApplication.class, args);
    }

    // Autowiring
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return args -> {
            // create
            String sql = "insert into users(name, email) values (?, ?)";
//            jdbcTemplate.update(sql, "kim", "a@gmail.com");

            // read
//            // BeanProperty 사용 (디폴트 생성자 사용)
//            List<User> users = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
//            users.forEach(System.out::println);

            // RowMapper를 이용해야 하는 이유: 결과값을 담을 때 User 클래스의 필드를 직접 매칭
            RowMapper<User> rowMapper = (ResultSet rs, int rowNum) -> new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

            List<User> users = jdbcTemplate.query("select * from users", rowMapper);
            users.forEach(System.out::println);

            // update
//            jdbcTemplate.update("update users set name = ? where id = ?", "park", "6");

            // delete
//            jdbcTemplate.update("delete from users where id = ?", 1);
        };
    }
}
