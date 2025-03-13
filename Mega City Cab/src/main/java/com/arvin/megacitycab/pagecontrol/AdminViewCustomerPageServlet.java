package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.model.base.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-view-customers")
public class AdminViewCustomerPageServlet extends BasePageServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)){
                //Api call
                List<User> users = UserAPIController.getAllCustomers();

                String searchTxt = request.getParameter("search");
                if (searchTxt != null){
                    List<User> newUsers = new ArrayList<>();
                    for (User u : users) {
                        if (u.toString().toLowerCase().contains(searchTxt.toLowerCase())){
                            newUsers.add(u);
                        }
                    };

                    users = newUsers;
                }

                request.setAttribute("users", users);
                request.getRequestDispatcher("admin-view-customers.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Invalid username or password, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
