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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/vehicle")
public class VehicleAPIServlet extends HttpServlet {

    private VehicleDao vehicleDao;

    @Override
    public void init() throws ServletException {
        vehicleDao = DaoFactory.vehicleDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            Vehicle getVehicle = requestToVehicle(request);

            if (getVehicle == null || getVehicle.getId() == 0) {
                customResponse(out, 400, "Invalid vehicle data or missing id.");
                return;
            }

            Vehicle vehicle = vehicleDao.getVehicleById(getVehicle.getId());
            if (vehicle != null) {
                out.print(new Gson().toJson(vehicle));
                out.flush();
            } else {
                customResponse(out, 404, "Vehicle not found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            Vehicle deleteVehicle = requestToVehicle(request);

            if (deleteVehicle == null || deleteVehicle.getId() == 0) {
                customResponse(out, 400, "Invalid vehicle data or missing id.");
                return;
            }

            vehicleDao.deleteVehicle(deleteVehicle.getId());
            customResponse(out, 200, "Vehicle deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            Vehicle vehicleToUpdate = requestToVehicle(request);

            if (vehicleToUpdate == null || vehicleToUpdate.getId() == 0) {
                customResponse(out, 400, "Invalid vehicle data or missing id.");
                return;
            }

            Vehicle updatedVehicle = vehicleDao.updateVehicle(vehicleToUpdate);
            if (updatedVehicle != null) {
                out.print(new Gson().toJson(updatedVehicle));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to update vehicle object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            Vehicle vehicle = requestToVehicle(request);

            if (vehicle != null) {
                if (vehicle.getMake() == null || vehicle.getModel() == null || vehicle.getRegistration_number() == null) {
                    customResponse(out, 400, "Required fields are empty.");
                    return;
                }

                vehicle = vehicleDao.addVehicle(vehicle);
                out.print(new Gson().toJson(vehicle));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to create vehicle object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    private Vehicle requestToVehicle(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void customResponse(PrintWriter out, int status, String msg) {
        out.print(new Gson().toJson(new ApiError(status, msg)));
        out.flush();
    }
}
