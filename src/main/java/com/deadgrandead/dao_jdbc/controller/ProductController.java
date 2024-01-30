package com.deadgrandead.dao_jdbc.controller;

import com.deadgrandead.dao_jdbc.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/fetch-product")
    public ResponseEntity<List<String>> fetchProduct(@RequestParam String name) {
        List<String> products = productRepository.getProductName(name);
        return ResponseEntity.ok(products);
    }
}

