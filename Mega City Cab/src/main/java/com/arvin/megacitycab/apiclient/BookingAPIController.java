package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

public class BookingAPIController {


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

}
