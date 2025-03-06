package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.apiclient.ApiClient;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-update-vehicle")
public class AdminUpdateVehiclePageServlet extends BasePageServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)) {
                //get data
                int vehicleId = Integer.parseInt(request.getParameter("id"));
                Vehicle vehicle = VehicleAPIController.getVehicleById(vehicleId);

                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("admin-update-vehicle.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to update vehicle at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-update-vehicle.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
