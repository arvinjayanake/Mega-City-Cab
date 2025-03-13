package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.arvin.megacitycab.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/form-new-user")
public class NewUserFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            User mUser = new User();
            mUser.setType(Integer.parseInt(request.getParameter("user_type")));
            mUser.setName(request.getParameter("name"));
            mUser.setNic(request.getParameter("nic"));
            mUser.setAddress(request.getParameter("address"));
            mUser.setEmail(request.getParameter("email"));
            mUser.setMobile(request.getParameter("mobile"));
            mUser.setIs_verified(1);
            mUser.setPassword(Util.toSHA256(request.getParameter("password")));
            mUser.setVerification_code("0000");

            //API call
            User user = UserAPIController.createUser(mUser);

            //redirect
            if (user.getType() == UserType.CUSTOMER.getValue()){
                response.sendRedirect("admin-view-customers");
            } else if (user.getType() == UserType.DRIVER.getValue()){
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
