package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.dao.BookingDao;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.arvin.megacitycab.model.enums.PaymentMethod;
import com.arvin.megacitycab.model.enums.UserType;
import com.arvin.megacitycab.util.ApiClient;
import com.arvin.megacitycab.util.Util;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;


@WebServlet("/form-book-a-taxi")
public class BookATaxiFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Logged user
            HttpSession session = request.getSession(true);
            User loggedUser = (User) session.getAttribute("user");

            //Data
            String pickup_location = request.getParameter("pickup_location");
            String pickup_time = request.getParameter("pickup_time");
            String drop_off_location = request.getParameter("drop_off_location");
            String vehicle = request.getParameter("vehicle");
            Double distance = Double.parseDouble(request.getParameter("distance"));
            Double price = Double.parseDouble(request.getParameter("price"));
            String vehicle_name = request.getParameter("vehicle_name");
            String payment_method = request.getParameter("payment_method");

            //API call
            String url = Config.API_URL_BASE + "booking";
            Map<String, Object> requestBody = Map.of(
                    "customer_id", loggedUser.getId(),
                    "vehicle_id", vehicle,
                    "pickup_location", pickup_location,
                    "pickup_datetime", pickup_time,
                    "dropoff_location", drop_off_location,
                    "total_distance", distance,
                    "total_price", price,
                    "status", BookingStatus.PENDING.getValue(),
                    "payment_method", payment_method
            );

            String apiResponse = ApiClient.post(url, requestBody);
            Booking booking = new Gson().fromJson(apiResponse, Booking.class);

            //set data
            request.setAttribute("booking", booking);
            request.setAttribute("vehicle_name", vehicle_name);

            int paymentMethodInt = Integer.parseInt(payment_method);
            if (paymentMethodInt == PaymentMethod.ONLINE.getValue()){
                request.getRequestDispatcher("booking-payment.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("booking-completed.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("book-a-taxi.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
