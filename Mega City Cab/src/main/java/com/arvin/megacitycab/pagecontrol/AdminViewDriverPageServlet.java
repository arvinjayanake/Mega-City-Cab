package com.arvin.megacitycab.pagecontrol;


import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-view-drivers")
public class AdminViewDriverPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Object userObj = session.getAttribute("user");

        try {
            //Api call
            String url = Config.API_URL_BASE + "users?type=2";
            String apiResponse = ApiClient.get(url);

            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(apiResponse, userListType);

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
            request.getRequestDispatcher("admin-view-drivers.jsp").forward(request, response);

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

//        if (userObj instanceof User) {
//            User user = (User) userObj;
//            if (user.getType() == UserType.ADMIN.getValue()){
//                request.getRequestDispatcher("admin-view-customers.jsp").forward(request, response);
//            } else {
//                response.sendRedirect("login");
//            }
//        } else {
//            response.sendRedirect("login");
//        }
    }
}
