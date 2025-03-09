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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/discount")
public class DiscountAPIServlet extends HttpServlet {

    private DiscountDao discountDao;

    @Override
    public void init() throws ServletException {
        discountDao = DaoFactory.discountDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            String discountId = request.getParameter("id");
            String discountCode = request.getParameter("discount_code");

            Discount discount = null;

            if (discountId != null){
                discount = discountDao.getDiscountById(Integer.parseInt(discountId));
            }

            if (discountCode != null){
                discount = discountDao.getDiscountByCode(discountCode);
            }

            if (discount != null) {
                out.print(new Gson().toJson(discount));
                out.flush();
            } else {
                customResponse(out, 404, "Discount not found");
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
            Discount deleteDiscount = requestToDiscount(request);

            if (deleteDiscount == null || deleteDiscount.getId() == 0) {
                customResponse(out, 400, "Invalid discount data or missing id.");
                return;
            }

            discountDao.deleteDiscount(deleteDiscount.getId());
            customResponse(out, 200, "Discount deleted.");
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
            Discount discountToUpdate = requestToDiscount(request);

            if (discountToUpdate == null || discountToUpdate.getId() == 0) {
                customResponse(out, 400, "Invalid discount data or missing id.");
                return;
            }

            Discount updatedDiscount = discountDao.updateDiscount(discountToUpdate);
            if (updatedDiscount != null) {
                out.print(new Gson().toJson(updatedDiscount));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to update discount object");
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
            Discount discount = requestToDiscount(request);

            if (discount != null) {
                if (discount.getCode() == null || discount.getType() == 0 || discount.getValue() == 0) {
                    customResponse(out, 400, "Required fields are empty.");
                    return;
                }

                discount = discountDao.addDiscount(discount);
                out.print(new Gson().toJson(discount));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to create discount object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    private Discount requestToDiscount(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), Discount.class);
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