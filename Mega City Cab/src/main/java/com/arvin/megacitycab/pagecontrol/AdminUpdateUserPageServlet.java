package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.config.Config;
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

@WebServlet("/admin-update-user")
public class AdminUpdateUserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get data
            String userId = request.getParameter("id");

            //api call
            String url = Config.API_URL_BASE + "user?id=" + userId;
            String apiResponse = ApiClient.get(url);

            //set data to jsp
            User user = new Gson().fromJson(apiResponse, User.class);
            request.setAttribute("user", user);
            request.getRequestDispatcher("admin-update-user.jsp").forward(request, response);
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Invalid username or password, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
