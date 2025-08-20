package com.example.stockmanager.domain.model;

public class Product {
    private Long id ;
    private String sku;
    private String description;

    public Product() {}

    public Product(Long id, String sku, String description) {
        this.id = id;
        this.sku = sku;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
