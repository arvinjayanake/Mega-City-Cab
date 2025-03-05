package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.PaymentDao;
import com.arvin.megacitycab.model.Payment;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/payment")
public class PaymentAPIServlet extends HttpServlet {

    private PaymentDao paymentDao;

    @Override
    public void init() throws ServletException {
        paymentDao = DaoFactory.paymentDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String paymentId = request.getParameter("id");
            if (paymentId == null || paymentId.isEmpty()) {
                customResponse(out, 400, "Invalid payment data or missing id.");
                return;
            }

            Payment payment = paymentDao.getPaymentById(Integer.parseInt(paymentId));
            if (payment != null) {
                out.print(new Gson().toJson(payment));
                out.flush();
            } else {
                customResponse(out, 404, "Payment not found");
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
            Payment payment = requestToPayment(request);
            if (payment != null) {
                payment = paymentDao.addPayment(payment);
                out.print(new Gson().toJson(payment));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to create payment.");
            }
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
            Payment payment = requestToPayment(request);

            if (payment == null || payment.getId() == 0) {
                customResponse(out, 400, "Invalid payment data or missing id.");
                return;
            }

            Payment updatedPayment = paymentDao.updatePayment(payment);
            if (updatedPayment != null) {
                out.print(new Gson().toJson(updatedPayment));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to update payment object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    private Payment requestToPayment(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), Payment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void customResponse(PrintWriter out, int status, String msg) {
        out.print(new Gson().toJson(new ApiError(status, msg)));
        out.flush();
    }
}