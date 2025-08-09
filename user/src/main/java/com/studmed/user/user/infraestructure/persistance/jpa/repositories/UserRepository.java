package com.studmed.user.user.infraestructure.persistance.jpa.repositories;

import com.studmed.user.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String username, String password);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndIdIsNot(String email, Long id);
}