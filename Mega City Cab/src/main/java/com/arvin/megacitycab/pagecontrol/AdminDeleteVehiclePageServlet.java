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

@WebServlet("/admin-delete-vehicle")
public class AdminDeleteVehiclePageServlet extends BasePageServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)){
                //get data
                int vehicleId = Integer.parseInt(request.getParameter("id"));

                //api call
                Vehicle vehicle = VehicleAPIController.getVehicleById(vehicleId);

                //set data to jsp
                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("admin-delete-vehicle.jsp").forward(request, response);
            }
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
