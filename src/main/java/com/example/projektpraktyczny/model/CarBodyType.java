package com.example.projektpraktyczny.model;

public enum CarBodyType {
    DID_NOT_SET(0),
    SUV(350),
    CABRIOLET(320),
    SEDAN(200);

    private final double pricePerDay;


    CarBodyType(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPrice(){
        return pricePerDay;
    }
}
