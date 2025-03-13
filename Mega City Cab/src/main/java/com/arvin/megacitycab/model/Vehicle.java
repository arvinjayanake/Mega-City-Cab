package com.arvin.megacitycab.model;

import com.arvin.megacitycab.model.enums.VehicleCategory;
import com.arvin.megacitycab.model.enums.VehicleStatus;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private int category;
    private int year;
    private String registration_number;
    private int passenger_capacity;
    private String luggage_capacity;
    private Double price_per_km;
    private int status;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public int getPassenger_capacity() {
        return passenger_capacity;
    }

    public void setPassenger_capacity(int passenger_capacity) {
        this.passenger_capacity = passenger_capacity;
    }

    public String getLuggage_capacity() {
        return luggage_capacity;
    }

    public void setLuggage_capacity(String luggage_capacity) {
        this.luggage_capacity = luggage_capacity;
    }

    public Double getPrice_per_km() {
        return price_per_km;
    }

    public void setPrice_per_km(Double price_per_km) {
        this.price_per_km = price_per_km;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String fullNameWithYear(){
        return  (getMake() != null ? getMake() : "-") + " " + (getModel() != null ? getModel() : "-") + " " + getYear();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", category=" + category +
                ", year=" + year +
                ", registration_number='" + registration_number + '\'' +
                ", passenger_capacity=" + passenger_capacity +
                ", luggage_capacity='" + luggage_capacity + '\'' +
                ", price_per_km=" + price_per_km +
                ", status=" + status +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
