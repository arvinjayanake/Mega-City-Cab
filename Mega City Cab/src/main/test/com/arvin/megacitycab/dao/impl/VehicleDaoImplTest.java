package com.arvin.megacitycab.dao.impl;

import com.arvin.megacitycab.dao.VehicleDao;
import com.arvin.megacitycab.model.Vehicle;
import com.arvin.megacitycab.model.enums.VehicleCategory;
import com.arvin.megacitycab.model.enums.VehicleStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VehicleDaoImplTest {

    private VehicleDao vehicleDao;
    private static Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicleDao = DaoFactory.vehicleDao();
    }

    @Test
    @Order(1)
    void addVehicleTest() {
        System.out.println("addVehicleTest() - Start");
        try {
            Vehicle v = new Vehicle();
            v.setMake("Suzuki");
            v.setModel("Swift RS");
            v.setCategory(VehicleCategory.HATCHBACK.getValue());
            v.setYear(2017);
            v.setRegistration_number("CBD6787");
            v.setPassenger_capacity(4);
            v.setLuggage_capacity("2 Large Bags");
            v.setPrice_per_km(150.00);
            v.setStatus(VehicleStatus.DEFAULT.getValue());
            vehicle = vehicleDao.addVehicle(v);
            System.out.println(vehicle.toString());
            assertTrue(true);
        }catch (Exception ex){
            ex.printStackTrace();
            fail(ex);
        }
        System.out.println("addVehicleTest() - End");
    }

    @Test
    @Order(2)
    void getVehicleByIdTest(){
        System.out.println("getVehicleByIdTest() - Start");
        try{
            Vehicle v = vehicleDao.getVehicleById(vehicle.getId());
            System.out.println(v.toString());
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail(ex);
        }
        System.out.println("getVehicleByIdTest() - End");
    }

    @Test
    @Order(3)
    void updateVehicleTest(){
        System.out.println("updateVehicleTest() - Start");
        try{
            vehicle.setYear(2018);
            vehicleDao.updateVehicle(vehicle);
            System.out.println(vehicle.toString());
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail(ex);
        }
        System.out.println("updateVehicleTest() - End");
    }

    @Test
    @Order(4)
    void deleteVehicleTest() {
        try {
            System.out.println("deleteVehicleTest() - Start");
            vehicleDao.deleteVehicle(vehicle.getId());
            assertTrue(true);
            System.out.println("deleteVehicleTest() - End");
        }catch (Exception ex){
            ex.printStackTrace();
            fail(ex);
        }
    }
}
