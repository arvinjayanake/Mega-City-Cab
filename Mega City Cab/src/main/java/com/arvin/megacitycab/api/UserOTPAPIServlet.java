package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/user/otp/verify")
public class UserOTPAPIServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = DaoFactory.userDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();

            JsonObject json = requestToJsonObject(request);

            if (json != null) {
                int id = json.get("id").getAsInt();
                String otp = json.get("otp").getAsString();

                User user = userDao.getUserById(id);

                if (user != null) {
                    if (user.getVerification_code().equals(otp)) {
                        user.setVerification_code(null);
                        user.setPassword(null);

                        //otp verified update
                        user.setVerified(true);
                        userDao.updateUser(user);

                        out.print(new Gson().toJson(user));
                        out.flush();
                    } else {
                        response.setStatus(401);
                        customResponse(out, 400, "Invalid OTP");
                    }
                } else {
                    response.setStatus(404);
                    customResponse(out, 400, "User not found");
                }
            } else {
                response.setStatus(400);
                customResponse(out, 400, "Unable to create user object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(500);
            customResponse(out, 500, ex.getMessage());
        }
    }


    private JsonObject requestToJsonObject(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), JsonObject.class);
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
