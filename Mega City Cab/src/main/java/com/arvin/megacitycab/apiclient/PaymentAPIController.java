package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Payment;
import com.arvin.megacitycab.model.enums.PaymentType;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

public class PaymentAPIController {

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
