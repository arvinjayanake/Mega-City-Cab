package com.arvin.megacitycab.dao;

public class DaoFactory {

    private DaoFactory() {
    }

    public static UserDao userDao() {
        return new UserDaoImpl();
    }

    public static VehicleDao vehicleDao() {
        return new VehicleDaoImpl();
    }

    public static BookingDao bookingDao() {
        return new BookingDaoImpl();
    }

    public static PaymentDao paymentDao(){
        return new PaymentDaoImpl();
    }
}
