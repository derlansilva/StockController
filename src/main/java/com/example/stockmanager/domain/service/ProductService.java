package com.example.stockmanager.domain.service;

import com.example.stockmanager.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
