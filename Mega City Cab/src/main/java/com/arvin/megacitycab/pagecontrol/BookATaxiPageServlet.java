package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.ConfigAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Config;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.enums.VehicleStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book-a-taxi")
public class BookATaxiPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Api call
            List<Vehicle> vehicles = VehicleAPIController.getAllVehicles();
            Config taxConfig = ConfigAPIController.getTaxConfig();

            //Filter available taxi
            List<Vehicle> availableVehicles = new ArrayList<>();
            for(Vehicle v : vehicles){
                if (v.getStatus() == VehicleStatus.AVAILABLE.getValue()){
                    availableVehicles.add(v);
                }
            }
            vehicles = availableVehicles;

            //ser data
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("tax_config", taxConfig);
            request.getRequestDispatcher("book-a-taxi.jsp").forward(request, response);
        }catch (Exception ex){
            ex.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load data at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("book-a-taxi.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
