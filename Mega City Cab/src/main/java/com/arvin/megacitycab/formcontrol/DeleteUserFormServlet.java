package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.model.enums.UserType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/form-delete-user")
public class DeleteUserFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String id = request.getParameter("id");
            int userType = Integer.parseInt(request.getParameter("user_type"));

            //API call
            UserAPIController.deleteUser(id);

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
