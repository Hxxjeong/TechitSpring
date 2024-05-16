package com.example.springjdbc08;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    // 입력이 되면 자동으로 생성된 PK 값 리턴
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    public User createUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName()); // 컬럼명, 객체
        params.put("email", user.getEmail());

        Number pk = simpleJdbcInsert.executeAndReturnKey(params);
        user.setId(pk.longValue());
        return user;
    }
}
