package com.example.springdatajpa.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // test에서는 기본 rollback
@Rollback(value = false)
class UserRepositoryTest {
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
}