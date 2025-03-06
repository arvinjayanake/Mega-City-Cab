package com.arvin.megacitycab.pagecontrol;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-new-user")
public class AdminNewUserPageServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAdmin(request, response)) {
            request.getRequestDispatcher("admin-new-user.jsp").forward(request, response);
        }
    }
}
