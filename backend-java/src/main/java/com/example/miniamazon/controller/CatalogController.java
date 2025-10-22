package com.example.miniamazon.controller;

import com.example.miniamazon.model.Product;
import com.example.miniamazon.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class CatalogController {

    private final ProductRepository productRepo;

    public CatalogController(ProductRepository productRepo) {
        this.productRepo = productRepo;

        // Seed demo products if DB is empty
        if (productRepo.count() == 0) {
            productRepo.saveAll(List.of(
                    new Product("Demo Product A", "Description A", 9.99, 10),
                    new Product("Demo Product B", "Description B", 19.99, 5)
            ));
        }
    }

    // GET /catalog - list all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // POST /catalog - add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    // DELETE /catalog/{id} - delete product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
    }
}
