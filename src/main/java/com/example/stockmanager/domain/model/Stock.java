package com.example.stockmanager.domain.model;

public class Stock {
    private long id ;
    private long availableQuantity ;
    private  long reservedQuantity ;
    private long lostQuantity ;

    public  Stock(){}

    public Stock(long id, long availableQuantity, long reservedQuantity, long lostQuantity) {
        this.id = id;

        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
        this.lostQuantity = lostQuantity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public long getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(long reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public long getLostQuantity() {
        return lostQuantity;
    }

    public void setLostQuantity(long lostQuantity) {
        this.lostQuantity = lostQuantity;
    }
}
