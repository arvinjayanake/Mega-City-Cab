package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.PaymentAPIController;
import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Booking;
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
import java.util.List;

@WebServlet("/admin-update-booking")
public class AdminEditBookingServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)) {
                int bookingId = Integer.parseInt(request.getParameter("id"));
                Booking booking = BookingAPIController.getBookingById(bookingId);
                User driver = UserAPIController.getUserById(booking.getDriver_id());
                List<User> drivers = UserAPIController.getAllDrivers();

                request.setAttribute("booking", booking);
                request.setAttribute("driver", driver);
                request.setAttribute("drivers", drivers);

                request.getRequestDispatcher("admin-update-booking.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load booking details at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-update-booking.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
