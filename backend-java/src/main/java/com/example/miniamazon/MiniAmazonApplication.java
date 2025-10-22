package com.example.miniamazon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.miniamazon.model.Product;
import com.example.miniamazon.repository.ProductRepository;
import com.example.miniamazon.service.UserService;

@SpringBootApplication
public class MiniAmazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniAmazonApplication.class, args);
    }

    // seed a couple demo products and a demo user
    @Bean
    CommandLineRunner seed(ProductRepository productRepository, UserService userService) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product("Demo Product A", "Description A", 9.99, 10));
                productRepository.save(new Product("Demo Product B", "Description B", 19.99, 5));
            }
            userService.createUserIfNotExists("demo@example.com", "password");
        };
    }
}
