package com.example.stockmanager.application.service;

import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.infrastructure.persistence.JpaProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    private final JpaProductRepository jpaProductRepository;

    public ProductServiceImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    public Product createProduct(Product product) {
        return jpaProductRepository.save(product);
    }

    public Product findProductBySku(String sku) {
        return jpaProductRepository.findBySku(sku)
                .orElseThrow( () -> new IllegalArgumentException("Product not found"));
    }
}
