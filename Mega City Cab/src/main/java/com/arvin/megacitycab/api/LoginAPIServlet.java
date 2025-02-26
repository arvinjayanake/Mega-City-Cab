package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.arvin.megacitycab.util.Util;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/user/login")
public class LoginAPIServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();

            User user = requestToUser(request);
            if (user == null || user.getEmail() == null || user.getEmail().isEmpty()
                    || user.getPassword() == null || user.getPassword().isEmpty()){
                response.setStatus(400);
                customResponse(out, 400, "Required fields are empty.");
                return;
            }

            user = userDao.login(user);

            if (user == null){
                response.setStatus(401);
                customResponse(out,401, "Unauthorized!");
                return;
            }
            user.setPassword(null);
            user.setVerification_code(null);

            //login success
            user.setAccess_token(Util.generateAccessToken());

            //update use with access token
            userDao.updateUser(user);

            out.print(new Gson().toJson(user));
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(500);
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
