package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.model.base.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home")
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");

        if (userObj instanceof User) {
            User user = (User) userObj;
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
}
