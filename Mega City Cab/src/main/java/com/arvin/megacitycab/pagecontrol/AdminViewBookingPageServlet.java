package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.model.Booking;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-view-bookings")
public class AdminViewBookingPageServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)) {
                //Api call
                List<Booking> bookings = BookingAPIController.getAllBookings();

                //Search
                String searchTxt = request.getParameter("search");
                if (searchTxt != null) {
                    List<Booking> newBookings = new ArrayList<>();
                    for (Booking b : bookings) {
                        if (b.toString().toLowerCase().contains(searchTxt.toLowerCase())) {
                            newBookings.add(b);
                        }
                    }

                    bookings = newBookings;
                }

                //set data
                request.setAttribute("bookings", bookings);
                request.getRequestDispatcher("admin-view-bookings.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load bookings at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-view-bookings.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
