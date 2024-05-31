package com.example.springdatajpa.user;

import com.example.springdatajpa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByNameOrEmail(String name, String email);

    @Modifying
    @Query("update User u set u.email = :email where u.id = :id")
    int updateUserEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query("delete from User u where u.email = :email")
    void deleteByEmail(@Param("email") String email);

    // native SQL을 사용한 사용자 조회 (email로 찾기)
    @Query(value = "select * from jpa_user where email like %?1%", nativeQuery = true)
    List<User> findByEmailNative(String email);

    // native SQL을 사용한 사용자 조회 (name으로 찾기)
    @Query(value = "select name, email from jpa_user where name like %:name%", nativeQuery = true)
    List<Object[]> findUserByNameNative(@Param("name") String name);
}
