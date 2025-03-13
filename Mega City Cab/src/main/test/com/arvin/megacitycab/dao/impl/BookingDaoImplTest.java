package com.arvin.megacitycab.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.arvin.megacitycab.dao.BookingDao;
import com.arvin.megacitycab.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BookingDaoImplTest {

    private BookingDao bookingDao;

    @BeforeEach
    void setUp() {
        bookingDao = DaoFactory.bookingDao();
    }

    @Test
    void getBookingsByVehicleId() throws SQLException {
        System.out.println("getBookingsByVehicleId() - Start");

        List<Booking> bookings = bookingDao.getBookingsByVehicleId(11);
        if (bookings != null) {
            System.out.println("Bookings: " + bookings.size());
            assertTrue(true);
        } else {
            System.out.println("Bookings: null");
            fail();
        }

        System.out.println("getBookingsByVehicleId() - End");
    }


}
