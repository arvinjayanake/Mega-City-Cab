package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.dao.VehicleDao;
import com.arvin.megacitycab.dao.base.BaseDao;
import com.arvin.megacitycab.db.DatabaseConnectionPool;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.enums.VehicleCategory;
import com.arvin.megacitycab.model.enums.VehicleStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl extends BaseDao implements VehicleDao {

    VehicleDaoImpl() {
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicle (make, model, category, year, registration_number, passenger_capacity, luggage_capacity, price_per_km, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, vehicle.getMake());
                stmt.setString(2, vehicle.getModel());
                stmt.setInt(3, vehicle.getCategory());
                stmt.setInt(4, vehicle.getYear());
                stmt.setString(5, vehicle.getRegistration_number());
                stmt.setInt(6, vehicle.getPassenger_capacity());
                stmt.setString(7, vehicle.getLuggage_capacity());
                stmt.setDouble(8, vehicle.getPrice_per_km());
                stmt.setInt(9, vehicle.getStatus());
                int rowsAffected = stmt.executeUpdate();

                // Retrieve the generated ID
                if (rowsAffected > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            vehicle = getVehicleById(generatedId);
                        }
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehicle vehicle = resultSetToVehicle(rs);
                    vehicleList.add(vehicle);
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return vehicleList;
    }

    @Override
    public Vehicle getVehicleById(int id) throws SQLException {
        Vehicle vehicle = null;
        String sql = "SELECT * FROM vehicle WHERE id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        vehicle = resultSetToVehicle(rs);
                    }
                }
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
        return vehicle;
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE vehicle SET ");
        List<Object> params = new ArrayList<>();

        if (vehicle.getMake() != null) {
            sql.append("make = ?, ");
            params.add(vehicle.getMake());
        }
        if (vehicle.getModel() != null) {
            sql.append("model = ?, ");
            params.add(vehicle.getModel());
        }
        if (VehicleCategory.isValid(vehicle.getCategory())) {
            sql.append("category = ?, ");
            params.add(vehicle.getCategory());
        }
        if (vehicle.getYear() != 0) {
            sql.append("year = ?, ");
            params.add(vehicle.getYear());
        }
        if (vehicle.getRegistration_number() != null) {
            sql.append("registration_number = ?, ");
            params.add(vehicle.getRegistration_number());
        }
        if (vehicle.getPassenger_capacity() != 0) {
            sql.append("passenger_capacity = ?, ");
            params.add(vehicle.getPassenger_capacity());
        }
        if (vehicle.getLuggage_capacity() != null) {
            sql.append("luggage_capacity = ?, ");
            params.add(vehicle.getLuggage_capacity());
        }
        if (vehicle.getPrice_per_km() != null) {
            sql.append("price_per_km = ?, ");
            params.add(vehicle.getPrice_per_km());
        }
        if (VehicleStatus.isValid(vehicle.getStatus())) {
            sql.append("status = ?, ");
            params.add(vehicle.getStatus());
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(vehicle.getId());

        Connection conn = null;
        try {
            conn = DatabaseConnectionPool.getInstance().getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }
                stmt.executeUpdate();

                return getVehicleById(vehicle.getId());
            }
        } finally {
            if (conn != null) {
                DatabaseConnectionPool.getInstance().releaseConnection(conn);
            }
        }
    }

    @Override
    public void deleteVehicle(int id) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE id=?";
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

    private Vehicle resultSetToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(rs.getInt("id"));
        vehicle.setMake(resToString(rs, "make"));
        vehicle.setModel(resToString(rs, "model"));
        vehicle.setCategory(rs.getInt("category"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setRegistration_number(resToString(rs, "registration_number"));
        vehicle.setPassenger_capacity(rs.getInt("passenger_capacity"));
        vehicle.setLuggage_capacity(resToString(rs, "luggage_capacity"));
        vehicle.setPrice_per_km(rs.getDouble("price_per_km"));
        vehicle.setStatus(rs.getInt("status"));
        vehicle.setCreated_at(resToString(rs, "created_at"));
        vehicle.setUpdated_at(resToString(rs, "updated_at"));
        return vehicle;
    }
}
