package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.Customer;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/form-register")
public class RegisterFormServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm-password");

            //Validation
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Password mismatch, please recheck your password.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Customer mUser = new Customer();
            mUser.setName(request.getParameter("name"));
            mUser.setNic(request.getParameter("nic"));
            mUser.setAddress(request.getParameter("address"));
            mUser.setEmail(request.getParameter("email"));
            mUser.setMobile(request.getParameter("mobile"));
            mUser.setPassword(Util.toSHA256(password));
            mUser.setVerification_code(Util.generateOTP());

            //Api call
            User user = UserAPIController.createUser(mUser);

            //Set data to session
            HttpSession session = request.getSession(true);
            session.setAttribute("user_id", user.getId());

            response.sendRedirect("register-otp");
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to register, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
