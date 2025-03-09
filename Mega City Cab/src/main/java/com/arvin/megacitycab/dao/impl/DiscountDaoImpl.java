package com.arvin.megacitycab.dao.impl;

import com.arvin.megacitycab.dao.DiscountDao;
import com.arvin.megacitycab.dao.base.BaseDao;
import com.arvin.megacitycab.db.DatabaseConnectionPool;
import com.arvin.megacitycab.model.Discount;
import com.arvin.megacitycab.model.enums.VehicleCategory;
import com.arvin.megacitycab.model.enums.VehicleStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DiscountDaoImpl extends BaseDao implements DiscountDao {

    DiscountDaoImpl() {}

    @Override
    public Discount addDiscount(Discount discount) throws SQLException {
        String sql = "INSERT INTO discount (code, type, value, enabled) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, discount.getCode());
                stmt.setInt(2, discount.getType());
                stmt.setInt(3, discount.getValue());
                stmt.setInt(4, discount.getEnabled());
                int rowsAffected = stmt.executeUpdate();

                // Retrieve the generated ID
                if (rowsAffected > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            discount = getDiscountById(generatedId);
                        }
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return discount;
    }

    @Override
    public List<Discount> getAllDiscounts() throws SQLException {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM discount ORDER BY id DESC";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Discount discount = resultSetToDiscount(rs);
                    discounts.add(discount);
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return discounts;
    }

    @Override
    public Discount getDiscountByCode(String code) throws SQLException {
        Discount discount = null;
        String sql = "SELECT * FROM discount WHERE code = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, code);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        discount = resultSetToDiscount(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return discount;
    }

    @Override
    public Discount getDiscountById(int id) throws SQLException {
        Discount discount = null;
        String sql = "SELECT * FROM discount WHERE id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        discount = resultSetToDiscount(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return discount;
    }

    @Override
    public Discount updateDiscount(Discount discount) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE discount SET ");
        List<Object> params = new ArrayList<>();

        if (discount.getCode() != null) {
            sql.append("code = ?, ");
            params.add(discount.getCode());
        }

        sql.append("type = ?, ");
        params.add(discount.getType());

        sql.append("value = ?, ");
        params.add(discount.getValue());

        sql.append("enabled = ?, ");
        params.add(discount.getEnabled());

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(discount.getId());

        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }
                stmt.executeUpdate();

                return getDiscountById(discount.getId());
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    @Override
    public void deleteDiscount(int id) throws SQLException {
        String sql = "DELETE FROM discount WHERE id=?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    private Discount resultSetToDiscount(ResultSet rs) throws SQLException {
        Discount discount = new Discount();
        discount.setId(rs.getInt("id"));
        discount.setCode(rs.getString("code"));
        discount.setType(rs.getInt("type"));
        discount.setValue(rs.getInt("value"));
        discount.setEnabled(rs.getInt("enabled"));
        discount.setCreated_at(rs.getString("created_at"));
        discount.setUpdated_at(rs.getString("updated_at"));
        return discount;
    }
}
