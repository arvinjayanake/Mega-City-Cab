package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-view-vehicles")
public class AdminViewVehiclePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Api call
            String url = Config.API_URL_BASE + "vehicles";
            String apiResponse = ApiClient.get(url);
            Type vehicleListType = new TypeToken<List<Vehicle>>() {}.getType();
            List<Vehicle> vehicles = new Gson().fromJson(apiResponse, vehicleListType);

            //search
            String searchTxt = request.getParameter("search");
            if (searchTxt != null){
                List<Vehicle> newVehicles = new ArrayList<>();
                for (Vehicle v : vehicles) {
                    if (v.toString().toLowerCase().contains(searchTxt.toLowerCase())){
                        newVehicles.add(v);
                    }
                };

                vehicles = newVehicles;
            }

            //set data
            request.setAttribute("vehicles", vehicles);
            request.getRequestDispatcher("admin-view-vehicles.jsp").forward(request, response);
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
