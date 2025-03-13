package com.arvin.megacitycab.api;

import com.arvin.megacitycab.api.error.ApiError;
import com.arvin.megacitycab.dao.BookingDao;
import com.arvin.megacitycab.dao.impl.DaoFactory;
import com.arvin.megacitycab.model.Booking;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/booking")
public class BookingAPIServlet extends HttpServlet {

    BookingDao bookingDao;

    @Override
    public void init() throws ServletException {
        bookingDao = DaoFactory.bookingDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            String bookingId = request.getParameter("id");

            if (bookingId == null || bookingId.isEmpty()) {
                customResponse(out, 400, "Invalid booking data or missing id.");
                return;
            }

            Booking booking = bookingDao.getBookingById(Integer.parseInt(bookingId));
            if (booking != null) {
                out.print(new Gson().toJson(booking));
                out.flush();
            } else {
                customResponse(out, 404, "Booking not found");
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

            Booking booking = requestToBooking(request);
            if (booking != null) {
                booking = bookingDao.addBooking(booking);
                out.print(new Gson().toJson(booking));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to create booking.");
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
            Booking booking = requestToBooking(request);

            if (booking == null || booking.getId() == 0) {
                customResponse(out, 400, "Invalid booking data or missing id.");
                return;
            }

            Booking updatedBooking = bookingDao.updateBooking(booking);
            if (updatedBooking != null) {
                out.print(new Gson().toJson(updatedBooking));
                out.flush();
            } else {
                customResponse(out, 400, "Unable to update booking object");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            customResponse(out, 500, ex.getMessage());
        }
    }

    private Booking requestToBooking(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonBody.toString(), Booking.class);
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
