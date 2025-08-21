package com.example.stockmanager.infrastructure.controller;


import com.example.stockmanager.application.service.ProductServiceImpl;
import com.example.stockmanager.application.service.StockServiceImpl;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/stock")
public class StockController {

    private final ProductServiceImpl productService;

    private final StockServiceImpl stockService;

    public StockController(ProductServiceImpl productService, StockServiceImpl stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }


    @PostMapping("/add")
    public ResponseEntity<Product> addProductStock(@PathVariable String sku , @RequestBody long quantity) {
        Product product = productService.findProductBySku(sku);
        stockService.addProductStrock(product , quantity);

        return ResponseEntity.ok(product);
    }

}
