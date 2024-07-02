package com.example.oauth2exam.repository;

import com.example.oauth2exam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByProviderAndSocialId(String provider, String socialId);
}
