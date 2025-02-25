package com.arvin.megacitycab.model;

import com.arvin.megacitycab.model.enums.BookingStatus;

public class Booking {
    private String bookingId;
    private Customer customer;
    private Driver driver;
    private Vehicle vehicle;
    private long pickupLatitude;
    private long pickupLongitude;
    private String pickupAddress;
    private long dropOffLatitude;
    private long dropOffLongitude;
    private String dropOffAddress;
    private BookingStatus bookingStatus;
    private double amount;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(long pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public long getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(long pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public long getDropOffLatitude() {
        return dropOffLatitude;
    }

    public void setDropOffLatitude(long dropOffLatitude) {
        this.dropOffLatitude = dropOffLatitude;
    }

    public long getDropOffLongitude() {
        return dropOffLongitude;
    }

    public void setDropOffLongitude(long dropOffLongitude) {
        this.dropOffLongitude = dropOffLongitude;
    }

    public String getDropOffAddress() {
        return dropOffAddress;
    }

    public void setDropOffAddress(String dropOffAddress) {
        this.dropOffAddress = dropOffAddress;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
