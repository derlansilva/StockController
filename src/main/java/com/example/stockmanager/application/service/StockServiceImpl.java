package com.example.stockmanager.application.service;

import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import com.example.stockmanager.infrastructure.persistence.JpaStockRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl {

    private final JpaStockRepository stockRepository;

    public StockServiceImpl(JpaStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public void addProductStrock(Product product , long quantity) {

    }

    public Optional<Stock> findByProductId(long id ){
        return stockRepository.findbyProductId(id);
    }

    public void createNewStock(Product product){
        stockRepository.createNewStock(product);
    }

    public void changeStockAvailabel(Product product , long quantity){
        stockRepository.changeAvailableStock(product , quantity);
    }

}
