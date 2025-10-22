package com.example.miniamazon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniamazon.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
