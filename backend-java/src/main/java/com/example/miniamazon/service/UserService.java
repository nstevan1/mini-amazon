package com.example.miniamazon.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.miniamazon.model.User;
import com.example.miniamazon.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User createUserIfNotExists(String email, String rawPassword) {
        return repo.findByEmail(email).orElseGet(() -> {
            User u = new User(email, encoder.encode(rawPassword));
            return repo.save(u);
        });
    }

    public User createUser(String email, String rawPassword) {
        User u = new User(email, encoder.encode(rawPassword));
        return repo.save(u);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return encoder.matches(rawPassword, user.getHashedPassword());
    }
}
