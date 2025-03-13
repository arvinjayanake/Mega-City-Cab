package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.model.base.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-delete-user")
public class AdminDeleteUserPageServlet extends BasePageServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)){
                //get data
                int userId = Integer.parseInt(request.getParameter("id"));

                //api call
                User user = UserAPIController.getUserById(userId);

                //set data to jsp
                request.setAttribute("user", user);
                request.getRequestDispatcher("admin-delete-user.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load admin page at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
