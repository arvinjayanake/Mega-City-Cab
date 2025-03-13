package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.model.Booking;

import java.sql.SQLException;
import java.util.List;

public interface BookingDao {
    Booking addBooking(Booking booking) throws SQLException;

    Booking getBookingById(int id) throws SQLException;

    List<Booking> getBookingsByVehicleId(int vehicleId) throws SQLException;

    List<Booking> getAllBookings() throws SQLException;

    Booking updateBooking(Booking booking) throws SQLException;

}
