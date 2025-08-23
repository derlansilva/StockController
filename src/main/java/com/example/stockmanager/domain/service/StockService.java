package com.example.stockmanager.domain.service;

import com.example.stockmanager.domain.model.MovementType;
import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;

import java.util.Optional;

public interface StockService {

    public Optional<Stock> findByProductId(long id);
    public void createNewStock(Product product);
    public void stockEntry(Product product, long quantity);
    public void stockExit(Product product , long quantity);
    public void transferStock(Product product , long quantity , MovementType from , MovementType to);
}
