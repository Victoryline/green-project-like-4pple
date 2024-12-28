package org.example.restserver.repository;

import org.example.restserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : UserRepository
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
