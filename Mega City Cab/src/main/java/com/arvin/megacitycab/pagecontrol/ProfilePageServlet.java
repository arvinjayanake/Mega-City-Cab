package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.model.base.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile")
public class ProfilePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Logged user
            HttpSession session = request.getSession(true);
            User loggedUser = (User) session.getAttribute("user");

            //set data
            request.setAttribute("user", loggedUser);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }catch (Exception ex){
            try {
                request.setAttribute("error", "Unable to load data at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
