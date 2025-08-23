package com.example.stockmanager.application.service;

import com.example.stockmanager.domain.model.MovementType;
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


    public Optional<Stock> findByProductId(long id ){
        return stockRepository.findbyProductId(id);
    }

    public void createNewStock(Product product){
        stockRepository.createNewStock(product);
    }

    public void stockEntry(Product product , long quantity){
        stockRepository.stockEntry(product , quantity);
    }

    public void stockExit(Product product , long quantity){

    }

    public void transferStock(Product product , long quantity , MovementType from , MovementType to){
        stockRepository.transferStock(product , quantity , from , to);
    }
}
