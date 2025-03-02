package com.arvin.megacitycab.navigate;


import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin-home")
public class AdminHomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");

        if (userObj instanceof User) {
            User user = (User) userObj;
            if (user.getType() == UserType.ADMIN.getValue()){
                request.getRequestDispatcher("admin-home.jsp").forward(request, response);
            } else {
                response.sendRedirect("login");
            }
        } else {
            response.sendRedirect("login");
        }
    }
}
