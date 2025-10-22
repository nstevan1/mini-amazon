package com.example.miniamazon.repository;

import com.example.miniamazon.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Optional: for search functionality (title contains query)
    List<Product> findByTitleContainingIgnoreCase(String title);
}
