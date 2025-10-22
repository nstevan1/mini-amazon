package com.example.miniamazon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.miniamazon.model.OrderEntity;
import com.example.miniamazon.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam String userEmail, @RequestParam(defaultValue = "0") double total) {
        // Very small placeholder: in a real app you'd validate cart, reserve inventory, etc.
        OrderEntity order = new OrderEntity(userEmail, total);
        orderRepo.save(order);
        return ResponseEntity.ok().body(java.util.Map.of("status", "ok", "message", "checkout simulated", "orderId", order.getId()));
    }
}
