package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.apiclient.ApiClient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;


@WebServlet("/form-delete-vehicle")
public class DeleteVehicleFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            int id = Integer.parseInt(request.getParameter("id"));

            //API call
            VehicleAPIController.deleteVehicleById(id);

            //redirect
            response.sendRedirect("admin-view-vehicles");
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
