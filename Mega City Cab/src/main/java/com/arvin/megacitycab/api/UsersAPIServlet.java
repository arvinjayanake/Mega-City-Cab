package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/users")
public class UsersAPIServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            int type = Integer.parseInt(request.getParameter("type"));
            if (isValidUserType(type)) {
                List<User> users = userDao.getAllUsers(type);
                out.print(new Gson().toJson(users));
                out.flush();
            } else {
                response.setStatus(400);
                errorResponse(out, response.getStatus(), "Bad request");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(500);
            errorResponse(out, 500, ex.getMessage());
        }
    }

    private void errorResponse(PrintWriter out, int status, String msg) {
        out.print(new Gson().toJson(new ApiError(status, msg)));
        out.flush();
    }

    private boolean isValidUserType(int type) {
        return type == UserType.CUSTOMER.getValue() || type == UserType.DRIVER.getValue()
                || type == UserType.ADMIN.getValue();
    }

}
