package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.arvin.megacitycab.util.ApiClient;
import com.arvin.megacitycab.util.Util;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;


@WebServlet("/form-update-vehicle")
public class UpdateVehicleFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String id = request.getParameter("id");
            String make = request.getParameter("make");
            String model = request.getParameter("model");
            String category = request.getParameter("category");
            String year = request.getParameter("year");
            String registration_number = request.getParameter("registration_number");
            String passenger_capacity = request.getParameter("passenger_capacity");
            String luggage_capacity = request.getParameter("luggage_capacity");
            String price_per_km = request.getParameter("price_per_km");
            String status = request.getParameter("status");

            //API call
            String url = Config.API_URL_BASE + "vehicle";
            Map<String, Object> requestBody = Map.of(
                    "id", id,
                    "make", make,
                    "model", model,
                    "category", category,
                    "year", year,
                    "registration_number", registration_number,
                    "passenger_capacity", passenger_capacity,
                    "luggage_capacity", luggage_capacity,
                    "price_per_km", price_per_km,
                    "status", status
            );
            String apiResponse = ApiClient.put(url, requestBody);
            Vehicle vehicle = new Gson().fromJson(apiResponse, Vehicle.class);

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
