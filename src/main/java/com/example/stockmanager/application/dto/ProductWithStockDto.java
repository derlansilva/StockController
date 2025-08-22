package com.example.stockmanager.application.dto;

import com.example.stockmanager.domain.model.Product;
import com.example.stockmanager.domain.model.Stock;

public class ProductWithStockDto {
    private long id ;
    private String sku;
    private String description;
    private String price;
    private Stock stock;

    public ProductWithStockDto(long id, String sku, String description, String price, Stock stock) {
        this.id = id;
        this.sku = sku;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
