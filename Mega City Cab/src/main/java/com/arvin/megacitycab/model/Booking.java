package com.arvin.megacitycab.model;

import com.arvin.megacitycab.model.enums.BookingStatus;
import com.arvin.megacitycab.model.enums.PaymentMethod;

public class Booking {

    private int id;
    private int customer_id;
    private int vehicle_id;
    private int driver_id = -1;
    private String pickup_location;
    private String pickup_datetime;
    private String dropoff_location;
    private Double total_distance;
    private Double total_price;
    private int status;
    private String created_at;
    private String updated_at;
    private int payment_method = PaymentMethod.CASH.getValue();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getPickup_datetime() {
        return pickup_datetime;
    }

    public void setPickup_datetime(String pickup_datetime) {
        this.pickup_datetime = pickup_datetime;
    }

    public String getDropoff_location() {
        return dropoff_location;
    }

    public void setDropoff_location(String dropoff_location) {
        this.dropoff_location = dropoff_location;
    }

    public Double getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(Double total_distance) {
        this.total_distance = total_distance;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
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

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }
}
