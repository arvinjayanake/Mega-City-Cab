package com.arvin.megacitycab.dao.impl;

import com.arvin.megacitycab.dao.BookingDao;
import com.arvin.megacitycab.dao.base.BaseDao;
import com.arvin.megacitycab.db.DatabaseConnectionPool;
import com.arvin.megacitycab.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class BookingDaoImpl extends BaseDao implements BookingDao {

    @Override
    public Booking addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO booking (customer_id, vehicle_id, driver_id, pickup_location, pickup_datetime, dropoff_location, total_distance, tax, discount, total_price, status, payment_method) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, booking.getCustomer_id());
                stmt.setInt(2, booking.getVehicle_id());
                stmt.setInt(3, booking.getDriver_id());
                stmt.setString(4, booking.getPickup_location());
                stmt.setString(5, booking.getPickup_datetime());
                stmt.setString(6, booking.getDropoff_location());
                stmt.setDouble(7, booking.getTotal_distance());
                stmt.setDouble(8, booking.getTax());
                stmt.setDouble(9, booking.getDiscount());
                stmt.setDouble(10, booking.getTotal_price());
                stmt.setInt(11, booking.getStatus());
                stmt.setInt(12, booking.getPayment_method());
                int rowsAffected = stmt.executeUpdate();

                // Retrieve the generated ID
                if (rowsAffected > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            booking = getBookingById(generatedId);
                        }
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return booking;
    }

    @Override
    public Booking getBookingById(int id) throws SQLException {
        Booking booking = null;
        String sql = "SELECT * FROM booking WHERE id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        booking = resultSetToBooking(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM booking ORDER BY id DESC";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = resultSetToBooking(rs);
                    bookings.add(booking);
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return bookings;
    }

    @Override
    public Booking updateBooking(Booking booking) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE booking SET ");
        List<Object> params = new ArrayList<>();

        if (booking.getCustomer_id() != 0) {
            sql.append("customer_id = ?, ");
            params.add(booking.getCustomer_id());
        }
        if (booking.getVehicle_id() != 0) {
            sql.append("vehicle_id = ?, ");
            params.add(booking.getVehicle_id());
        }
        if (booking.getDriver_id() != -1) {
            sql.append("driver_id = ?, ");
            params.add(booking.getDriver_id());
        }
        if (booking.getPickup_location() != null) {
            sql.append("pickup_location = ?, ");
            params.add(booking.getPickup_location());
        }
        if (booking.getPickup_datetime() != null) {
            sql.append("pickup_datetime = ?, ");
            params.add(booking.getPickup_datetime());
        }
        if (booking.getDropoff_location() != null) {
            sql.append("dropoff_location = ?, ");
            params.add(booking.getDropoff_location());
        }
        if (booking.getTotal_distance() != null) {
            sql.append("total_distance = ?, ");
            params.add(booking.getTotal_distance());
        }
        if (booking.getTax() != null && booking.getTax() > 0.0) {
            sql.append("tax = ?, ");
            params.add(booking.getTax());
        }
        if (booking.getDiscount() != null && booking.getDiscount() > 0.0) {
            sql.append("discount = ?, ");
            params.add(booking.getDiscount());
        }
        if (booking.getTotal_price() != null) {
            sql.append("total_price = ?, ");
            params.add(booking.getTotal_price());
        }
        if (booking.getStatus() != 0) {
            sql.append("status = ?, ");
            params.add(booking.getStatus());
        }
        if (booking.getPayment_method() == 1 || booking.getPayment_method() == 2) {
            sql.append("payment_method = ?, ");
            params.add(booking.getPayment_method());
        }
        if (booking.getUpdated_at() != null) {
            sql.append("updated_at = ?, ");
            params.add(booking.getUpdated_at());
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(booking.getId());

        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }
                stmt.executeUpdate();

                return getBookingById(booking.getId());
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }


    private Booking resultSetToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setCustomer_id(rs.getInt("customer_id"));
        booking.setVehicle_id(rs.getInt("vehicle_id"));
        booking.setDriver_id(rs.getInt("driver_id"));
        booking.setPickup_location(rs.getString("pickup_location"));
        booking.setPickup_datetime(rs.getString("pickup_datetime"));
        booking.setDropoff_location(rs.getString("dropoff_location"));
        booking.setTotal_distance(rs.getDouble("total_distance"));
        booking.setTax(rs.getDouble("tax"));
        booking.setDiscount(rs.getDouble("discount"));
        booking.setTotal_price(rs.getDouble("total_price"));
        booking.setStatus(rs.getInt("status"));
        booking.setPayment_method(rs.getInt("payment_method"));
        booking.setCreated_at(rs.getString("created_at"));
        booking.setUpdated_at(rs.getString("updated_at"));
        return booking;
    }
}
