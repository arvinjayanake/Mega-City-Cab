package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.dao.DiscountDao;
import com.arvin.megacitycab.model.Discount;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/discounts")
public class DiscountsAPIServlet extends HttpServlet {

    private DiscountDao discountDao;

    @Override
    public void init() throws ServletException {
        discountDao = DaoFactory.discountDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();

            // Fetch all discounts from the database
            List<Discount> discounts = discountDao.getAllDiscounts();
            out.print(new Gson().toJson(discounts));
            out.flush();
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
}