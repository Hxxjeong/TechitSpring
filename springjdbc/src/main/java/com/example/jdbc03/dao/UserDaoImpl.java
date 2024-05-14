package com.example.jdbc03.dao;

import com.example.jdbc03.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(User user) {
        String sql = "insert into users(name, email) values (?, ?)";
        try {
            jdbcTemplate.update(sql, user.getName(), user.getEmail());
        }
        catch (DataAccessException e) {
            throw new DataAccessException("사용자 입력 중 오류 발생: " + user.getName(), e) {};
        }
    }

    @Override
    public List<User> findAllUsers() {
        try {
            RowMapper<User> users = (ResultSet rs, int rowNum) -> new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

            List<User> userList = jdbcTemplate.query("select * from users", users);

            return userList;
        }
        catch (DataAccessException e) {
            throw new DataAccessException("데이터 조회 중 오류 발생: " + e) {};
        }
    }

    @Override
    public void updateUserEmail(String name, String email) {
        String sql = "update users set email = ? where name = ?";
        int update = jdbcTemplate.update(sql, email, name);
        if(update == 0)
            throw new UserNotFoundException(name + "에 해당하는 유저가 없습니다.");
    }

    @Override
    public void deleteUser(String name) {
        String sql = "delete from users where name = ?";
        int delete = jdbcTemplate.update(sql, name);
        if(delete == 0)
            throw new UserNotFoundException(name + "에 해당하는 유저가 없습니다.");
    }
}
