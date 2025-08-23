package com.example.stockmanager.application.dto;

import com.example.stockmanager.domain.model.MovementType;

public class TransferRequestDto {
    private String sku;
    private long quantity;
    private MovementType fromStockType;
    private MovementType toStockType;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public MovementType getFromStockType() {
        return fromStockType;
    }

    public void setFromStockType(MovementType fromStockType) {
        this.fromStockType = fromStockType;
    }

    public MovementType getToStockType() {
        return toStockType;
    }

    public void setToStockType(MovementType toStockType) {
        this.toStockType = toStockType;
    }
}
