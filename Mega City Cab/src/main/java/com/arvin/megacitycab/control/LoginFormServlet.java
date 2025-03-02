package com.arvin.megacitycab.control;

import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
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


@WebServlet("/form-login")
public class LoginFormServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Api call
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String url = "http://localhost:8089/mega_city_cab_war_exploded/api/user/login";
            Map<String, Object> requestBody = Map.of(
                    "email", email,
                    "password", Util.toSHA256(password)
            );
            String apiResponse = ApiClient.post(url, requestBody);
            User user = new Gson().fromJson(apiResponse, User.class);

            //Set data to session
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);


            if (user.getType() == UserType.ADMIN.getValue()){
                response.sendRedirect("admin-home");
            } else {
                response.sendRedirect("home");
            }


        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Invalid username or password, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
