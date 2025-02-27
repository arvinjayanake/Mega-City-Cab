package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.VehicleDao;
import com.arvin.megacitycab.dao.VehicleDaoImpl;
import com.arvin.megacitycab.model.Vehicle;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/vehicles")
public class VehiclesAPIServlet extends HttpServlet {

    VehicleDao vehicleDao;

    @Override
    public void init() throws ServletException {
        vehicleDao = DaoFactory.vehicleDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();

            List<Vehicle> vehicles = vehicleDao.getAllVehicles();
            out.print(new Gson().toJson(vehicles));
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(500);
            errorResponse(out, 500, ex.getMessage());
        }
    }

    private void errorResponse(PrintWriter out, int status, String msg) {
        out.print(new Gson().toJson(new ApiError(status, msg)));
        out.flush();
    }

}
