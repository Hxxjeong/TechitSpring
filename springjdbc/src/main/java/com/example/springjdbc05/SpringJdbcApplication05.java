package com.example.springjdbc05;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJdbcApplication05 {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication05.class, args);
    }

    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return args -> {
            String sql = "select * from users";
            
            // ResultSetExtractor 결과: 집합 전체를 단일 객체로 변환하는 데 사용
            // 여러 테이블에서 조인된 결과를 받아 하나의 통합 객체로 매핑 (특정 조건에 따라 다양한 타입의 객체를 리스트에 추가할 때 사용)
            // 메소드가 한번만 호출돼서 전체 결과 집합 처리
            User user = jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
                @Override
                public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                    User user = new User();
                    if(rs.next()) {
                        user.setId(rs.getLong("id"));
                        user.setName((rs.getString("name")));
                        user.setEmail(rs.getString("email"));
                        
                        // user 하나에 대한 값만 채움 (RowMapper와 비슷한 결과)
                        // 1:N 테이블의 경우 N 관계의 튜플이 리스트로 들어올 수 있음

                        // 관계 리스트가 있는 경우
//                        List<Role> roles = new ArrayList<>();
                    }
                    return user;
                }
            });
            System.out.println(user);
        };
    }
}
