package com.arvin.megacitycab.pagecontrol;

import com.arvin.megacitycab.apiclient.PaymentAPIController;
import com.arvin.megacitycab.model.Payment;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-view-payments")
public class AdminViewPaymentPageServlet extends BasePageServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (isAdmin(request, response)) {
                //Api call
                List<Payment> payments = PaymentAPIController.getAllPayments();

                //search
                String searchTxt = request.getParameter("search");
                if (searchTxt != null) {
                    List<Payment> newPayments = new ArrayList<>();
                    for (Payment p : payments) {
                        if (p.toString().toLowerCase().contains(searchTxt.toLowerCase())) {
                            newPayments.add(p);
                        }
                    }

                    payments = newPayments;
                }

                //set data
                request.setAttribute("payments", payments);
                request.getRequestDispatcher("admin-view-payments.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to load payments at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-view-payments.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
