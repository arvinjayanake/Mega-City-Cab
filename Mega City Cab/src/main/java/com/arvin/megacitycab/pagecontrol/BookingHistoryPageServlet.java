package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.VehicleStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/booking-history")
public class BookingHistoryPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Logged user
            HttpSession session = request.getSession(true);
            User loggedUser = (User) session.getAttribute("user");

            //Api call
            List<Booking> bookings = BookingAPIController.getUserBookings(loggedUser.getId());

            //ser data
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("booking-history.jsp").forward(request, response);
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load data at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("booking-history.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
