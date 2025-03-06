package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.PaymentAPIController;
import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Customer;
import com.arvin.megacitycab.model.Payment;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.PaymentMethod;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-booking-details")
public class AdminViewBookingDetailsServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)) {
                int bookingId = Integer.parseInt(request.getParameter("id"));
                Booking booking = BookingAPIController.getBookingById(bookingId);
                User customer = UserAPIController.getUserById(booking.getCustomer_id());
                Vehicle vehicle = VehicleAPIController.getVehicleById(booking.getVehicle_id());
                Payment payment = null;

                if (booking.getPayment_method() == PaymentMethod.ONLINE.getValue()) {
                    payment = PaymentAPIController.getPaymentByBookingId(bookingId);
                }

                request.setAttribute("booking", booking);
                request.setAttribute("customer", customer);
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("payment", payment);
                request.getRequestDispatcher("admin-booking-details.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load booking details at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-booking-details.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
