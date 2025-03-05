package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.arvin.megacitycab.model.enums.PaymentMethod;
import com.arvin.megacitycab.apiclient.ApiClient;
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

            String vehicle_name = request.getParameter("vehicle_name");

            //Data
            Booking mBooking = new Booking();
            mBooking.setCustomer_id(loggedUser.getId());
            mBooking.setPickup_location(request.getParameter("pickup_location"));
            mBooking.setPickup_datetime(request.getParameter("pickup_time"));
            mBooking.setDropoff_location(request.getParameter("drop_off_location"));
            mBooking.setVehicle_id(Integer.parseInt(request.getParameter("vehicle")));
            mBooking.setTotal_distance(Double.parseDouble(request.getParameter("distance")));
            mBooking.setTotal_price(Double.parseDouble(request.getParameter("price")));
            mBooking.setPayment_method(Integer.parseInt(request.getParameter("payment_method")));
            mBooking.setStatus(BookingStatus.PENDING.getValue());

            //API call
            Booking booking = BookingAPIController.createBooking(mBooking);

            //set data
            request.setAttribute("booking", booking);
            request.setAttribute("vehicle_name", vehicle_name);

            if (mBooking.getPayment_method() == PaymentMethod.ONLINE.getValue()){
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
