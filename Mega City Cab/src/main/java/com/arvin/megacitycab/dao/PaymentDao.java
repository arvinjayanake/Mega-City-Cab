package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.model.Booking;
import com.arvin.megacitycab.model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao {
    Payment addPayment(Payment payment) throws SQLException;

    Payment getPaymentById(int id) throws SQLException;

    List<Payment> getAllPayments() throws SQLException;

    Payment updatePayment(Payment payment) throws SQLException;

}
