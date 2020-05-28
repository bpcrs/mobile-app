package com.example.bpcrsadmin.model;

import java.io.Serializable;

public class Car implements Serializable {
    private String model;
    private String year;
    private String carNumber;

    public Car() {
    }

    public Car(String model, String year, String carNumber) {
        this.model = model;
        this.year = year;
        this.carNumber = carNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
