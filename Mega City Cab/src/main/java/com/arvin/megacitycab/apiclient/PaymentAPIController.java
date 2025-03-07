package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class PaymentAPIController {

    public static Payment getPaymentByBookingId(int bookingId) throws IOException {
        String url = Config.API_URL_BASE + "payments";
        String apiResponse = ApiClient.get(url);
        Gson gson = new Gson();
        Type paymentListType = new TypeToken<List<Payment>>() {
        }.getType();
        List<Payment> payments = gson.fromJson(apiResponse, paymentListType);
        for (Payment p : payments) {
            if (p.getBooking_id() == bookingId) {
                return p;
            }
        }

        return null;
    }

    public static Payment getPaymentById(int id) throws IOException {
        String url = Config.API_URL_BASE + "payment?id=" + id;
        String apiResponse = ApiClient.get(url);
        return new Gson().fromJson(apiResponse, Payment.class);
    }

    public static Payment createPayment(Payment payment) throws IOException {
        String url = Config.API_URL_BASE + "payment";
        Map<String, Object> requestBody = Map.of(
                "booking_id", payment.getBooking_id(),
                "card_no", payment.getCard_no(),
                "amount", payment.getAmount(),
                "type", payment.getType()
        );

        String apiResponse = ApiClient.post(url, requestBody);
        return new Gson().fromJson(apiResponse, Payment.class);
    }

}
