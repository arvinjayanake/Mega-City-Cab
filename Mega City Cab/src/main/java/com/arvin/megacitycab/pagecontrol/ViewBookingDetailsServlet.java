package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.PaymentAPIController;
import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Payment;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.arvin.megacitycab.model.enums.PaymentMethod;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/booking-details")
public class ViewBookingDetailsServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isCustomerOrDriver(request, response)) {
                int bookingId = Integer.parseInt(request.getParameter("id"));
                Booking booking = BookingAPIController.getBookingById(bookingId);
                User user = UserAPIController.getUserById(booking.getCustomer_id());
                Vehicle vehicle = VehicleAPIController.getVehicleById(booking.getVehicle_id());
                Payment payment = null;
                Payment refund = null;
                User driver = null;

                if (booking.getPayment_method() == PaymentMethod.ONLINE.getValue()) {
                    payment = PaymentAPIController.getBookingPayment(bookingId);

                    if (booking.getStatus() == BookingStatus.REJECTED.getValue()){
                        refund = PaymentAPIController.getBookingRefundPayment(bookingId);
                    }
                }

                if (booking.getDriver_id() != -1){
                    driver = UserAPIController.getUserById(booking.getDriver_id());
                }

                request.setAttribute("booking", booking);
                request.setAttribute("user", user);
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("payment", payment);
                request.setAttribute("refund", refund);
                request.setAttribute("driver", driver);
                request.getRequestDispatcher("booking-details.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load booking details at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("booking-details.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
