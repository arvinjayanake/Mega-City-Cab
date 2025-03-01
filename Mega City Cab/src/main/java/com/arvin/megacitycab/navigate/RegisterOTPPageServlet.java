package com.arvin.megacitycab.navigate;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/register-otp")
public class RegisterOTPPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute("user_id");

        if (userId != null){
            request.getRequestDispatcher("register-otp.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
}
