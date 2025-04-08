package com.example.emailauth.repository;
import com.example.emailauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    // 토큰으로 사용자 조회
    Optional<User> findByToken(String token);
    
}
