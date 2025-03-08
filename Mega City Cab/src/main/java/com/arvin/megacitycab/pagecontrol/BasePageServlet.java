package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class BasePageServlet extends HttpServlet {

    protected boolean isCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object loggedUser = session.getAttribute("user");
        if (loggedUser instanceof User) {
            User user = (User) loggedUser;
            if (user.getType() == UserType.CUSTOMER.getValue()) {
                return true;
            }
        }

        request.setAttribute("error", "Unauthorized access!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);

        return false;
    }

    protected boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object loggedUser = session.getAttribute("user");
        if (loggedUser instanceof User) {
            User user = (User) loggedUser;
            if (user.getType() == UserType.ADMIN.getValue()) {
                return true;
            }
        }

        request.setAttribute("error", "Unauthorized access!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);

        return false;
    }

}
