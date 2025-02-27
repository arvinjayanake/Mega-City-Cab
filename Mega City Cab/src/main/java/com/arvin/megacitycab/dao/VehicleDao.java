package com.arvin.megacitycab.dao;

import com.arvin.megacitycab.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao {
    Vehicle addVehicle(Vehicle vehicle) throws SQLException;
    List<Vehicle> getAllVehicles() throws SQLException;
    Vehicle getVehicleById(int id) throws SQLException;
    Vehicle updateVehicle(Vehicle vehicle) throws SQLException;
    void deleteVehicle(int id) throws SQLException;
}
