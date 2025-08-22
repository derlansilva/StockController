package com.example.stockmanager.infrastructure.controller;


import com.example.stockmanager.application.service.ProductServiceImpl;
import com.example.stockmanager.application.service.StockServiceImpl;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final ProductServiceImpl productService;

    private final StockServiceImpl stockService;

    public StockController(ProductServiceImpl productService, StockServiceImpl stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }


    @PutMapping("/update/{sku}")
    public ResponseEntity<Product> updateStockAvailable(@PathVariable("sku") String sku , @RequestParam("quantity") long quantity) {
        Product product = productService.findProductBySku(sku);
        stockService.changeStockAvailabel(product , quantity);

        return ResponseEntity.ok(product);
    }

}
