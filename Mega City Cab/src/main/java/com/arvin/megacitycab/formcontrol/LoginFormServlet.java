package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.arvin.megacitycab.apiclient.ApiClient;
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
            User user = UserAPIController.login(email, password);

            //Set data to session
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);


            if (user.getType() == UserType.CUSTOMER.getValue()){
                response.sendRedirect("book-a-taxi");
            } else if (user.getType() == UserType.ADMIN.getValue()){
                response.sendRedirect("admin-view-customers");
            } else {
                response.sendRedirect("booking-history");
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
