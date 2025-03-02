package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.arvin.megacitycab.util.ApiClient;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;


@WebServlet("/form-delete-user")
public class DeleteUserFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String id = request.getParameter("id");
            int userType = Integer.parseInt(request.getParameter("user_type"));

            //API call
            String url = Config.API_URL_BASE + "user";
            Map<String, Object> requestBody = Map.of("id", id);
            ApiClient.delete(url, requestBody);

            //redirect
            if (userType == UserType.CUSTOMER.getValue()) {
                response.sendRedirect("admin-view-customers");
            } else if (userType == UserType.DRIVER.getValue()) {
                response.sendRedirect("admin-view-drivers");
            } else {
                response.sendRedirect("admin-view-admins");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register-otp.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
