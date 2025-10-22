package com.example.miniamazon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String status = "created";

    private double total = 0.0;

    public OrderEntity() {}

    public OrderEntity(String userEmail, double total) {
        this.userEmail = userEmail;
        this.total = total;
    }

    // getters/setters
    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String u) { this.userEmail = u; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public double getTotal() { return total; }
    public void setTotal(double t) { this.total = t; }
}
