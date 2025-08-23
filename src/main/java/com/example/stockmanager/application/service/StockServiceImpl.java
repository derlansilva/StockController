package com.example.stockmanager.application.service;

import com.example.stockmanager.domain.model.MovementType;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;
import com.example.stockmanager.domain.service.StockService;
import com.example.stockmanager.infrastructure.persistence.JpaStockRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final JpaStockRepository stockRepository;

    public StockServiceImpl(JpaStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Optional<Stock> findByProductId(long id ){
        return stockRepository.findStockbyProductId(id);
    }

    @Override
    public void createNewStock(Product product){
        stockRepository.createNewStock(product);
    }

    @Override
    public void stockEntry(Product product , long quantity){
        stockRepository.stockEntry(product , quantity);
    }

    @Override
    public void stockExit(Product product, long quantity) {

    }

    @Override
    public void transferStock(Product product , long quantity , MovementType from , MovementType to){
        stockRepository.transferStock(product , quantity , from , to);
    }
}
