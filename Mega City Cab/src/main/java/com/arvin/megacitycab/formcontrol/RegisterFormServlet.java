package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.ApiClient;
import com.arvin.megacitycab.util.Util;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;


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
            String name = request.getParameter("name");
            String nic = request.getParameter("nic");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String mobile = request.getParameter("mobile");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm-password");

            //Validation
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Password mismatch, please recheck your password.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
                dispatcher.forward(request, response);
                return;
            }


            //API call
            String otp = Util.generateOTP();

            String url = Config.API_URL_BASE + "user";
            Map<String, Object> requestBody = Map.of(
                    "name", name,
                    "nic", nic,
                    "email", email,
                    "address", address,
                    "verification_code", otp,
                    "mobile", mobile,
                    "password", Util.toSHA256(password)
            );
            String apiResponse = ApiClient.post(url, requestBody);
            User user = new Gson().fromJson(apiResponse, User.class);

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

//            request.setAttribute("error", "Invalid username or password, please try again.");
//            try {
//                response.sendRedirect("login");
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
        }
    }
}
