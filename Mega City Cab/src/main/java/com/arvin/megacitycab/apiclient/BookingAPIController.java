package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.AppConfig;
import com.arvin.megacitycab.model.Booking;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingAPIController {

    public static Booking updateBooking(Booking booking) throws IOException {
        String url = AppConfig.API_URL_BASE + "booking";

        Map<String, Object> requestBody = Map.of(
                "id", booking.getId(),
                "driver_id", booking.getDriver_id(),
                "status", booking.getStatus(),
                "payment_method", booking.getPayment_method()
        );

        String apiResponse = ApiClient.put(url, requestBody);
        return new Gson().fromJson(apiResponse, Booking.class);
    }

    public static Booking createBooking(Booking booking) throws IOException {
        String url = AppConfig.API_URL_BASE + "booking";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("customer_id", booking.getCustomer_id());
        requestBody.put("vehicle_id", booking.getVehicle_id());
        requestBody.put("pickup_location", booking.getPickup_location());
        requestBody.put("pickup_datetime", booking.getPickup_datetime()); // Ensure this is not null
        requestBody.put("dropoff_location", booking.getDropoff_location());
        requestBody.put("total_distance", booking.getTotal_distance());
        requestBody.put("tax", booking.getTax());
        requestBody.put("discount", booking.getDiscount());
        requestBody.put("total_price", booking.getTotal_price());
        requestBody.put("status", booking.getStatus());
        requestBody.put("payment_method", booking.getPayment_method());

        String apiResponse = ApiClient.post(url, requestBody);
        return new Gson().fromJson(apiResponse, Booking.class);
    }

    public static Booking getBookingById(int id) throws IOException {
        String bookingUrl = AppConfig.API_URL_BASE + "booking?id=" + id;
        String bookingRes = ApiClient.get(bookingUrl);
        return new Gson().fromJson(bookingRes, Booking.class);
    }

    public static List<Booking> getUserBookings(int userId) throws IOException {
        List<Booking> bookings = new ArrayList<>();

        for (Booking b : getAllBookings()) {
            if (b.getCustomer_id() == userId) {
                bookings.add(b);
            }
        }

        return bookings;
    }

    public static List<Booking> getDriverBookings(int driverId) throws IOException {
        List<Booking> bookings = new ArrayList<>();

        for (Booking b : getAllBookings()) {
            if (b.getDriver_id() == driverId) {
                bookings.add(b);
            }
        }

        return bookings;
    }

    public static List<Booking> getBookingsByVehicleId(int vehicleId) throws IOException {
        String url = AppConfig.API_URL_BASE + "bookings?vehicle_id=" + vehicleId;
        String apiResponse = ApiClient.get(url);
        Type bookingListType = new TypeToken<List<Booking>>() {}.getType();
        return new Gson().fromJson(apiResponse, bookingListType);
    }

    public static List<Booking> getAllBookings() throws IOException {
        String url = AppConfig.API_URL_BASE + "bookings";
        String apiResponse = ApiClient.get(url);
        Type bookingListType = new TypeToken<List<Booking>>() {
        }.getType();
        return new Gson().fromJson(apiResponse, bookingListType);
    }

}
