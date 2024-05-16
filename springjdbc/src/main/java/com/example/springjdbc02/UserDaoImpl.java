package com.example.springjdbc02;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    // newEmail에 error 문자열이 포함되어 있으면 rollback (insert 처리 취소)
    @Override
    public void createAndUpdateUser(String name, String email, String newEmail) {
        jdbcTemplate.update("insert into users(name, email) values(?, ?)", name, email);
        if(newEmail.contains("error")) {    // 의도적으로 에러 발생
            throw new RuntimeException("Simulated error");
        }
        jdbcTemplate.update("update users set email = ? where name = ?", newEmail, name);
    }
}
