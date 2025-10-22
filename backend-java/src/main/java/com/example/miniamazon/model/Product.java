package com.example.miniamazon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(columnDefinition="text")
    private String description;

    private double price;

    private int inventory;

    public Product() {}

    public Product(String title, String description, double price, int inventory) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
    }

    // getters/setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public double getPrice() { return price; }
    public void setPrice(double p) { this.price = p; }
    public int getInventory() { return inventory; }
    public void setInventory(int i) { this.inventory = i; }
}
