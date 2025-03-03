package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.ApiClient;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-delete-vehicle")
public class AdminDeleteVehiclePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get data
            String vehicleId = request.getParameter("id");

            //api call
            String url = Config.API_URL_BASE + "vehicle?id=" + vehicleId;
            String apiResponse = ApiClient.get(url);
            Vehicle vehicle = new Gson().fromJson(apiResponse, Vehicle.class);

            //set data to jsp
            request.setAttribute("vehicle", vehicle);
            request.getRequestDispatcher("admin-delete-vehicle.jsp").forward(request, response);
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to delete vehicle at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-delete-vehicle.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
