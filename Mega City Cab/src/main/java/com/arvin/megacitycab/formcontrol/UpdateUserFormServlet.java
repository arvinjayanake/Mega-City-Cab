package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
            User mUser = new User();
            mUser.setId(Integer.parseInt(request.getParameter("id")));
            mUser.setName(request.getParameter("name"));
            mUser.setNic(request.getParameter("nic"));
            mUser.setAddress(request.getParameter("address"));
            mUser.setEmail(request.getParameter("email"));
            mUser.setMobile(request.getParameter("mobile"));
            mUser.setIs_verified(Integer.parseInt(request.getParameter("is_verified")));
            mUser.setPassword(request.getParameter("password"));

            //Api call
            User user = UserAPIController.updateUser(mUser);

            if (user != null) {
                if (user.getType() == UserType.CUSTOMER.getValue()) {
                    response.sendRedirect("admin-view-customers");
                } else if (user.getType() == UserType.DRIVER.getValue()) {
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
