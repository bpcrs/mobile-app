package com.example.bpcrsadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Car implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("vin")
    @Expose
    private String vin;

    public Car(int id, String name, double price, String vin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vin = vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
