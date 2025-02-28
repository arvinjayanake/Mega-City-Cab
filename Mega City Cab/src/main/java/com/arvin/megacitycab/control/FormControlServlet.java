package com.arvin.megacitycab.control;

import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.ApiClient;
import com.arvin.megacitycab.util.Util;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class FormControlServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getServletPath();

        try {
            if (path.equals("/login-submit")) {
                login(request, response);
            } else if (path.equals("/logout-submit")){
                logOut(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        try {
            request.getRequestDispatcher("login").forward(request, response);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
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

            response.sendRedirect("home");
        } catch (Exception e1) {
            e1.printStackTrace();
            request.setAttribute("error", "Invalid username or password, please try again.");
            try {
                request.getRequestDispatcher("login").forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
