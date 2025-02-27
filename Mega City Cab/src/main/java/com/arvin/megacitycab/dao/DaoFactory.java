package com.arvin.megacitycab.dao;

public class DaoFactory {

    private DaoFactory() {}

    public static UserDao userDao() {
        return new UserDaoImpl();
    }

    public static VehicleDao vehicleDao() {
        return new VehicleDaoImpl();
    }
}
