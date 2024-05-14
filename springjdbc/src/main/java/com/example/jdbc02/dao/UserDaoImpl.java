package com.example.jdbc02.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor    // final 필드에 대해 생성자 추가
public class UserDaoImpl implements UserDao {
    // JdbcTemplate 객체 이용
    // 생성자 기반
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(User user) {
        String sql = "insert into users(name, email) values (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        RowMapper<User> users = (ResultSet rs, int rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );

        List<User> userList = jdbcTemplate.query("select * from users", users);

        return userList;

        // class를 따로 만드는 경우
//        return jdbcTemplate.query("select * from users", new UserMapper());
    }

    @Override
    public void updateUserEmail(String name, String email) {
        String sql = "update users set email = ? where name = ?";
        jdbcTemplate.update(sql, email, name);
    }

    @Override
    public void deleteUser(String name) {
        String sql = "delete from users where name = ?";
        jdbcTemplate.update(sql, name);
    }

/*    static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );
            return user;
        }
    }*/
}
