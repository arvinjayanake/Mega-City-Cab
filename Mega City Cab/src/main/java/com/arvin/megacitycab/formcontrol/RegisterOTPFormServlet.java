package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.apiclient.ApiClient;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;


@WebServlet("/form-register-otp")
public class RegisterOTPFormServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            String otp = request.getParameter("otp");

            //API call
            User user = UserAPIController.userOtpVerify(user_id, otp);

            if (user != null){
                //Set data to session
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

                response.sendRedirect("book-a-taxi");
            } else {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register-otp.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register-otp.jsp");
                dispatcher.forward(request, response);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
