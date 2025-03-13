package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Booking;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


@WebServlet("/form-delete-vehicle")
public class DeleteVehicleFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int id = 0;
        try {
            //Data
            id = Integer.parseInt(request.getParameter("id"));

            List<Booking> bookings = BookingAPIController.getBookingsByVehicleId(id);

            if (bookings == null || bookings.size() == 0){
                VehicleAPIController.deleteVehicleById(id);
                response.sendRedirect("admin-delete-vehicle?id=" + id);
            } else {
                response.sendRedirect("admin-delete-vehicle?id=" + id + "&error=" + "Unable to delete. This vehicle has bookings.");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                response.sendRedirect("admin-delete-vehicle?id=" + id + "&error=" + "Error while deleting the vehicle. Try again.");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
