package com.example.stockmanager.application.service;

import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.infrastructure.persistence.JpaStockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl {

    private final JpaStockRepository stockRepository;

    public StockServiceImpl(JpaStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public void addProductStrock(Product product , long quantity) {

    }
}
