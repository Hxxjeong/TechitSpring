package com.example.springdatajpa.user;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // test에서는 기본 rollback
@Rollback(value = false)
class UserRepositoryTest {
    private final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        User user = new User("park", "park@gmail.com");
        userRepository.save(user);

        System.out.println(user.getId());
    }

    @Test
    void findByName() {
        List<User> users = userRepository.findByName("kim");
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void findByEmail(){
        List<User> users = userRepository.findByEmail("choi@gmail.com");
        users.forEach(user -> System.out.println(user.getName() + "::" + user.getEmail()));
    }

    @Test
    void findByNameAndEmail(){
        List<User> users = userRepository.findByNameAndEmail("park", "park@gmail.com");
        users.forEach(user -> System.out.println(user.getName() + "::" + user.getEmail()));
    }

    @Test
    void findByNameOrEmail(){
        List<User> users = userRepository.findByNameOrEmail("hong", "jung@gmail.com");
        users.forEach(user -> System.out.println(user.getName() + "::" + user.getEmail()));
    }

    @Test
    void updateUserEmail() {
        userRepository.updateUserEmail(1L, "hello@gmail.com");
        Optional<User> user = userRepository.findById(1L);
        assertThat(user.get().getEmail()).isEqualTo("hello@gmail.com");
    }

    @Test
    void deleteUserEmail() {
        userRepository.deleteByEmail("kim@gmail.com");
        List<User> users = userRepository.findAll();
        List<String> userEmails = users.stream()
                .map(User::getEmail)
                .toList();

        assertThat(userEmails).doesNotContain("kim@gmail.com");
    }

    @Test
    void findUserNative() {
        List<User> users = userRepository.findByEmailNative("park@gmail.com");
        assertThat(users).extracting(User::getName).contains("park");
    }

    @Test
    void findUser() {
        List<Object[]> users = userRepository.findUserByNameNative("j");
        List<UserDto> userDtos = users.stream()
                .map(result -> new UserDto((String) result[0], (String) result[1]))
                .collect(Collectors.toList());

        userDtos.forEach(userDto -> log.info("사용자: {}, 이메일: {}", userDto.getName(), userDto.getEmail()));
    }
}