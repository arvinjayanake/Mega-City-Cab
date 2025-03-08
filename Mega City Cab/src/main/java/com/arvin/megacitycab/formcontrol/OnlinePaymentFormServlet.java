package com.arvin.megacitycab.formcontrol;

import com.arvin.megacitycab.apiclient.BookingAPIController;
import com.arvin.megacitycab.apiclient.PaymentAPIController;
import com.arvin.megacitycab.apiclient.VehicleAPIController;
import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Payment;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.enums.PaymentType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/form-online-payment")
public class OnlinePaymentFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Payment object
            Payment mPayment = new Payment();
            mPayment.setBooking_id(Integer.parseInt(request.getParameter("booking_id")));
            mPayment.setCard_no(request.getParameter("card_number"));
            mPayment.setAmount(Double.parseDouble(request.getParameter("amount")));
            mPayment.setType(PaymentType.PAYMENT.getValue());

            //Payment api
            Payment payment = PaymentAPIController.createPayment(mPayment);

            //booking object api
            Booking booking = BookingAPIController.getBookingById(payment.getBooking_id());

            //Vehicle object api
            Vehicle vehicle = VehicleAPIController.getVehicleById(booking.getVehicle_id());

            //set data
            request.setAttribute("payment", payment);
            request.setAttribute("booking", booking);
            request.setAttribute("vehicle_name", vehicle.fullNameWithYear());

            //navigate
            request.getRequestDispatcher("booking-completed.jsp").forward(request, response);
        } catch (Exception e1) {
            e1.printStackTrace();
            try {
                request.setAttribute("error", "Unable to verify OTP, please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("booking-payment.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
