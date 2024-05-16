package com.example.springjdbc03;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public void executeComplexBusinessProcess(String name, String email) {
        transactionTemplate.execute(status -> {
            jdbcTemplate.update("insert into users(name, email) values(?, ?)", name, email);
            if(email.contains("error")) {
                status.setRollbackOnly();   // rollback

                throw new RuntimeException("Simulated error to trigger rollback");
            }
            jdbcTemplate.update("update users set email = ? where name = ?", email, name);
            return null;
        });
    }
}
