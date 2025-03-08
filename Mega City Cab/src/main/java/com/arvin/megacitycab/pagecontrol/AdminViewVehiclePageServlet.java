package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Vehicle;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-view-vehicles")
public class AdminViewVehiclePageServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)) {
                //Api call
                List<Vehicle> vehicles = VehicleAPIController.getAllVehicles();

                //search
                String searchTxt = request.getParameter("search");
                if (searchTxt != null) {
                    List<Vehicle> newVehicles = new ArrayList<>();
                    for (Vehicle v : vehicles) {
                        if (v.toString().toLowerCase().contains(searchTxt.toLowerCase())) {
                            newVehicles.add(v);
                        }
                    }
                    ;

                    vehicles = newVehicles;
                }

                //set data
                request.setAttribute("vehicles", vehicles);
                request.getRequestDispatcher("admin-view-vehicles.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load vehicles at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-view-vehicles.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
