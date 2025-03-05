package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Payment;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.arvin.megacitycab.model.enums.PaymentMethod;
import com.arvin.megacitycab.model.enums.PaymentType;
import com.arvin.megacitycab.util.ApiClient;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.awt.print.Book;
import java.util.Map;


@WebServlet("/form-online-payment")
public class OnlinePaymentFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String card_number = request.getParameter("card_number");
            String booking_id = request.getParameter("booking_id");
            String amount = request.getParameter("amount");

            //booking object api
            String bookingUrl = Config.API_URL_BASE + "booking?id=" + booking_id;
            String bookingRes = ApiClient.get(bookingUrl);
            Booking booking = new Gson().fromJson(bookingRes, Booking.class);

            //Vehicle object api
            String vehicleUrl = Config.API_URL_BASE + "vehicle?id=" + booking.getVehicle_id();
            String vehicleRes = ApiClient.get(vehicleUrl);
            Vehicle vehicle = new Gson().fromJson(vehicleRes, Vehicle.class);

            //Payment api call
            String url = Config.API_URL_BASE + "payment";
            Map<String, Object> requestBody = Map.of(
                    "booking_id", booking_id,
                    "card_no", card_number,
                    "amount", amount,
                    "type", PaymentType.PAYMENT
            );

            String apiResponse = ApiClient.post(url, requestBody);
            Payment payment = new Gson().fromJson(apiResponse, Payment.class);

            //set data
            request.setAttribute("payment", payment);
            request.setAttribute("booking", booking);
            request.setAttribute("vehicle_name", vehicle.fullNameWithYear());

            //navigate
            request.getRequestDispatcher("booking-completed.jsp").forward(request, response);
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("booking-payment.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
