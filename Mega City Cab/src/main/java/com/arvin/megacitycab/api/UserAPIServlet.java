package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/user")
public class UserAPIServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            String userId = request.getParameter("id");

            if (userId == null || userId.isEmpty()) {
                customResponse(out, 400, "Invalid user data or missing id.");
                return;
            }

            User user = userDao.getUserById(Integer.parseInt(userId));
            if (user != null) {
                user.setPassword(null);
                out.print(new Gson().toJson(user));
                out.flush();
            } else {
                customResponse(out, 404, "User not found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            User deleteUser = requestToUser(request);

            if (deleteUser == null || deleteUser.getId() == 0) {
                customResponse(out, 400, "Invalid user data or missing id.");
                return;
            }

            userDao.deleteUser(deleteUser.getId());
            customResponse(out, 200, "User deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();

            User userToUpdate = requestToUser(request);

            if (userToUpdate == null || userToUpdate.getId() == 0) {
                customResponse(out, 400, "Invalid user data or missing id.");
                return;
            }

            User updateduser = userDao.updateUser(userToUpdate);
            if (updateduser != null) {
                out.print(new Gson().toJson(updateduser));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to update user object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();

            User user = requestToUser(request);
            if (user != null) {
                if (user.getName() == null || user.getEmail() == null || user.getMobile() == null) {
                    customResponse(out, 400, "Required fields are empty.");
                    return;
                }

                user = userDao.addUser(user);
                out.print(new Gson().toJson(user));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to create user object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    private User requestToUser(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void customResponse(PrintWriter out, int status, String msg) {
        out.print(new Gson().toJson(new ApiError(status, msg)));
        out.flush();
    }

    private boolean isValidUserType(int type) {
        return type == UserType.CUSTOMER.getValue() || type == UserType.DRIVER.getValue()
                || type == UserType.ADMIN.getValue();
    }

}
