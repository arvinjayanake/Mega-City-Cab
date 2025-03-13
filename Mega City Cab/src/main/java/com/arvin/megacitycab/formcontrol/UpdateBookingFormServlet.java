package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.PaymentAPIController;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Payment;
import com.arvin.megacitycab.model.base.User;
import com.arvin.megacitycab.model.enums.BookingStatus;
import com.arvin.megacitycab.model.enums.PaymentMethod;
import com.arvin.megacitycab.model.enums.PaymentType;
import com.arvin.megacitycab.model.enums.UserType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/form-update-booking")
public class UpdateBookingFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            User loggedUser = (User) session.getAttribute("user");

            //Data
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            int driverId = Integer.parseInt(request.getParameter("driver_id"));
            int status = Integer.parseInt(request.getParameter("status"));

            //get booking and set data
            Booking booking = BookingAPIController.getBookingById(bookingId);

            //set data and update booking api
            booking.setDriver_id(driverId);
            booking.setStatus(status);
            BookingAPIController.updateBooking(booking);

            //Change payment to refund if rejected
            if (booking.getStatus() == BookingStatus.REJECTED.getValue()) {
                if (booking.getPayment_method() == PaymentMethod.ONLINE.getValue()) {
                    Payment payment = PaymentAPIController.getBookingPayment(booking.getId());
                    if (payment != null){
                        //insert refund object
                        Payment refundPayment = new Payment();
                        refundPayment.setBooking_id(booking.getId());
                        refundPayment.setCard_no(payment.getCard_no());
                        refundPayment.setAmount(payment.getAmount());
                        refundPayment.setType(PaymentType.REFUND.getValue());

                        PaymentAPIController.createPayment(refundPayment);
                    }
                }
            }

            //get data for view booking page
            if (loggedUser.getType() == UserType.ADMIN.getValue()){
                response.sendRedirect("admin-view-bookings");
            } else if (loggedUser.getType() == UserType.DRIVER.getValue()){
                response.sendRedirect("booking-history");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to update booking at the moment.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-update-booking.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
