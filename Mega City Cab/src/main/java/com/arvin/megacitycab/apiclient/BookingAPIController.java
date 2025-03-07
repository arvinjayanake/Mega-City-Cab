package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingAPIController {

    public static Booking updateBooking(Booking booking) throws IOException {
        String url = Config.API_URL_BASE + "booking";

        Map<String, Object> requestBody = Map.of(
                "id", booking.getId(),
                "driver_id", booking.getDriver_id(),
                "status", booking.getStatus()
        );

        String apiResponse = ApiClient.put(url, requestBody);
        return new Gson().fromJson(apiResponse, Booking.class);
    }

    public static Booking createBooking(Booking booking) throws IOException {
        String url = Config.API_URL_BASE + "booking";

        Map<String, Object> requestBody = Map.of(
                "customer_id", booking.getCustomer_id(),
                "vehicle_id", booking.getVehicle_id(),
                "pickup_location", booking.getPickup_location(),
                "pickup_datetime", booking.getPickup_datetime(),
                "dropoff_location", booking.getDropoff_location(),
                "total_distance", booking.getTotal_distance(),
                "total_price", booking.getTotal_price(),
                "status", booking.getStatus(),
                "payment_method", booking.getPayment_method()
        );

        String apiResponse = ApiClient.post(url, requestBody);
        return new Gson().fromJson(apiResponse, Booking.class);
    }

    public static Booking getBookingById(int id) throws IOException {
        String bookingUrl = Config.API_URL_BASE + "booking?id=" + id;
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

    public static List<Booking> getAllBookings() throws IOException {
        String url = Config.API_URL_BASE + "bookings";
        String apiResponse = ApiClient.get(url);
        Type bookingListType = new TypeToken<List<Booking>>() {
        }.getType();
        return new Gson().fromJson(apiResponse, bookingListType);
    }

}
