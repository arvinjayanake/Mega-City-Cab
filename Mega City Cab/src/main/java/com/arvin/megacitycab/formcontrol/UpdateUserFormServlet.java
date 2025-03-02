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
import jakarta.servlet.http.HttpSession;

import java.util.Map;


@WebServlet("/form-update-user")
public class UpdateUserFormServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String nic = request.getParameter("nic");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");

            //API call
            String url = Config.API_URL_BASE + "user";
            Map<String, Object> requestBody = Map.of(
                    "id", id,
                    "name", name,
                    "nic", nic,
                    "address", address,
                    "email", email,
                    "mobile", mobile
            );
            String apiResponse = ApiClient.put(url, requestBody);
            User user = new Gson().fromJson(apiResponse, User.class);

            if (user != null) {
                if (user.getType() == UserType.CUSTOMER.getValue()){
                    response.sendRedirect("admin-view-customers");
                } else if (user.getType() == UserType.DRIVER.getValue()){
                    response.sendRedirect("admin-view-drivers");
                } else {
                    response.sendRedirect("admin-view-admins");
                }
            } else {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-view-customers");
                dispatcher.forward(request, response);
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
