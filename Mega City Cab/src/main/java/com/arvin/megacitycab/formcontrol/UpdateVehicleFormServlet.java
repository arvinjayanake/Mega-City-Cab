package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Vehicle;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/form-update-vehicle")
public class UpdateVehicleFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            Vehicle mVehicle = new Vehicle();
            mVehicle.setId(Integer.parseInt(request.getParameter("id")));
            mVehicle.setMake(request.getParameter("make"));
            mVehicle.setModel(request.getParameter("model"));
            mVehicle.setCategory(Integer.parseInt(request.getParameter("category")));
            mVehicle.setYear(Integer.parseInt(request.getParameter("year")));
            mVehicle.setRegistration_number(request.getParameter("registration_number"));
            mVehicle.setPassenger_capacity(Integer.parseInt(request.getParameter("passenger_capacity")));
            mVehicle.setLuggage_capacity(request.getParameter("luggage_capacity"));
            mVehicle.setPrice_per_km(Double.parseDouble(request.getParameter("price_per_km")));
            mVehicle.setStatus(Integer.parseInt(request.getParameter("status")));

            //API call
            VehicleAPIController.updateVehicle(mVehicle);

            //redirect
            response.sendRedirect("admin-view-vehicles");
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register-otp.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
