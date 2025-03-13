package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.ConfigDao;
import com.arvin.megacitycab.dao.VehicleDao;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.model.Config;
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

@WebServlet("/api/config")
public class ConfigAPIServlet extends HttpServlet {

    private ConfigDao configDao;

    @Override
    public void init() throws ServletException {
        configDao = DaoFactory.configDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            int configId = Integer.parseInt(request.getParameter("id"));

            Config config = configDao.getConfigById(configId);

            if (config != null) {
                out.print(new Gson().toJson(config));
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            Config configToUpdate = requestToConfig(request);

            if (configToUpdate == null || configToUpdate.getId() == 0) {
                customResponse(out, 400, "Invalid config data or missing id.");
                return;
            }

            Config updatedConfig = configDao.updateConfig(configToUpdate);
            if (updatedConfig != null) {
                out.print(new Gson().toJson(updatedConfig));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to update vehicle object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    private Config requestToConfig(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), Config.class);
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
