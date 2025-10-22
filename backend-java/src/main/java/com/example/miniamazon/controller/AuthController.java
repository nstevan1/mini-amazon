package com.example.miniamazon.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.miniamazon.model.User;
import com.example.miniamazon.service.UserService;
import com.example.miniamazon.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    record RegisterPayload(String email, String password) {}
    record TokenResponse(String access_token, String token_type) {}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterPayload p) {
        Optional<User> existing = userService.findByEmail(p.email());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already registered"));
        }
        User u = userService.createUser(p.email(), p.password());
        String token = jwtUtil.generateToken(u.getEmail());
        return ResponseEntity.ok(new TokenResponse(token, "bearer"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated RegisterPayload p) {
        Optional<User> userOpt = userService.findByEmail(p.email());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
        User u = userOpt.get();
        if (!userService.checkPassword(u, p.password())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
        String token = jwtUtil.generateToken(u.getEmail());
        return ResponseEntity.ok(new TokenResponse(token, "bearer"));
    }
}
