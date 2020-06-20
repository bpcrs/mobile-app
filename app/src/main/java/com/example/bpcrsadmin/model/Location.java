package com.example.bpcrsadmin.model;

public class Location {
    private int carId;
    private String latitude;
    private String longitude;
    private String time;

    public Location(int carId, String latitude, String longitude, String time) {
        this.carId = carId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public Location() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
