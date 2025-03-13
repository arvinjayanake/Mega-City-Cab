package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.UserAPIController;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.model.Customer;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.util.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/form-update-profile")
public class UpdateProfileFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Data
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");

            //Validation
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Password mismatch, please recheck your password.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Customer mUser = new Customer();
            mUser.setId(Integer.parseInt(request.getParameter("id")));
            mUser.setAddress(request.getParameter("address"));
            mUser.setMobile(request.getParameter("mobile"));
            mUser.setIs_verified(1);

            if (!password.isEmpty()){
                mUser.setPassword(Util.toSHA256(password));
            }

            //Api call
            User user = UserAPIController.updateUser(mUser);

            //Set data to session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to register, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
