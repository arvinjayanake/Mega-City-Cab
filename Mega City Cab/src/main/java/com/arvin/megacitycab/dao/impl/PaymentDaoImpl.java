package com.arvin.megacitycab.dao.impl;

import com.arvin.megacitycab.dao.PaymentDao;
import com.arvin.megacitycab.dao.base.BaseDao;
import com.arvin.megacitycab.db.DatabaseConnectionPool;
import com.arvin.megacitycab.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class PaymentDaoImpl extends BaseDao implements PaymentDao {

    @Override
    public Payment addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (booking_id, card_no, amount, type) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, payment.getBooking_id());
                stmt.setString(2, payment.getCard_no());
                stmt.setDouble(3, payment.getAmount());
                stmt.setInt(4, payment.getType());
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            payment = getPaymentById(generatedId);
                        }
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return payment;
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        Payment payment = null;
        String sql = "SELECT * FROM payment WHERE id = ?";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        payment = resultSetToPayment(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return payment;
    }

    @Override
    public Payment getPaymentByBookingId(int bookingId) throws SQLException {
        Payment payment = null;
        String sql = "SELECT * FROM payment WHERE booking_id = ?";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, bookingId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        payment = resultSetToPayment(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment ORDER BY id DESC";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Payment payment = resultSetToPayment(rs);
                    payments.add(payment);
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return payments;
    }

    @Override
    public Payment updatePayment(Payment payment) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE payment SET ");
        List<Object> params = new ArrayList<>();

        if (payment.getBooking_id() != 0) {
            sql.append("booking_id = ?, ");
            params.add(payment.getBooking_id());
        }
        if (payment.getPayment_date() != null) {
            sql.append("payment_date = ?, ");
            params.add(payment.getPayment_date());
        }
        if (payment.getCard_no() != null) {
            sql.append("card_no = ?, ");
            params.add(payment.getCard_no());
        }
        if (payment.getAmount() != null) {
            sql.append("amount = ?, ");
            params.add(payment.getAmount());
        }
        if (payment.getType() != 0) {
            sql.append("type = ?, ");
            params.add(payment.getType());
        }

        if (params.isEmpty()) {
            return payment;
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(payment.getId());

        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }
                stmt.executeUpdate();

                return getPaymentById(payment.getId());
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    private Payment resultSetToPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setBooking_id(rs.getInt("booking_id"));
        payment.setPayment_date(rs.getString("payment_date"));
        payment.setCard_no(rs.getString("card_no"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setType(rs.getInt("type"));
        return payment;
    }

}
