package com.example.stockmanager.infrastructure.controller;


import com.example.stockmanager.application.dto.ProductWithStockDto;
import com.example.stockmanager.application.service.ProductServiceImpl;
import com.example.stockmanager.application.service.StockServiceImpl;
import com.example.stockmanager.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductServiceImpl productService;


    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;

    }



    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);

        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductWithStockDto> getProductBySku(@PathVariable("sku") String sku) {
        System.out.println("Sku " + sku);
        ProductWithStockDto product = productService.findProductAndStockBySku(sku);

        return ResponseEntity.ok(product);

    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.findAllProducts();
    }


}
