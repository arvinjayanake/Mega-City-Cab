package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.BookingDao;
import com.arvin.megacitycab.dao.DaoFactory;
import com.arvin.megacitycab.dao.UserDao;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.UserType;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/bookings")
public class BookingsAPIServlet extends HttpServlet {

    BookingDao bookingDao;

    @Override
    public void init() throws ServletException {
        bookingDao = DaoFactory.bookingDao();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            List<Booking> bookings = bookingDao.getAllBookings();
            out.print(new Gson().toJson(bookings));
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

    private boolean isValidUserType(int type) {
        return type == UserType.CUSTOMER.getValue() || type == UserType.DRIVER.getValue()
                || type == UserType.ADMIN.getValue();
    }

}
