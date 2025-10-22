package com.example.miniamazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.miniamazon.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> { }
