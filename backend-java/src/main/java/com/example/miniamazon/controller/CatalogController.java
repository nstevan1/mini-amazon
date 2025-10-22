package com.example.miniamazon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.miniamazon.model.Product;
import com.example.miniamazon.repository.ProductRepository;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductRepository productRepo;

    public CatalogController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product payload) {
        Product p = productRepo.save(payload);
        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(@RequestParam(defaultValue = "0") int skip,
                                                      @RequestParam(defaultValue = "50") int limit) {
        List<Product> all = productRepo.findAll();
        int from = Math.min(skip, all.size());
        int to = Math.min(skip + limit, all.size());
        return ResponseEntity.ok(all.subList(from, to));
    }
}
