package com.example.miniamazon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    private boolean active = true;

    public User() {}

    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    // getters/setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
