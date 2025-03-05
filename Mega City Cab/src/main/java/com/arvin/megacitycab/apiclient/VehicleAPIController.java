package com.arvin.megacitycab.apiclient;

import com.arvin.megacitycab.config.Config;
import com.arvin.megacitycab.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class VehicleAPIController {


    public static void updateVehicle(Vehicle vehicle) throws IOException {
        String url = Config.API_URL_BASE + "vehicle";
        Map<String, Object> requestBody = Map.of(
                "id", vehicle.getId(),
                "make", vehicle.getMake(),
                "model", vehicle.getModel(),
                "category", vehicle.getCategory(),
                "year", vehicle.getYear(),
                "registration_number", vehicle.getRegistration_number(),
                "passenger_capacity", vehicle.getPassenger_capacity(),
                "luggage_capacity", vehicle.getLuggage_capacity(),
                "price_per_km", vehicle.getPrice_per_km(),
                "status", vehicle.getStatus()
        );
        ApiClient.put(url, requestBody);
    }

    public static void createVehicle(Vehicle vehicle) throws IOException {
        String url = Config.API_URL_BASE + "vehicle";
        Map<String, Object> requestBody = Map.of(
                "make", vehicle.getMake(),
                "model", vehicle.getModel(),
                "category", vehicle.getCategory(),
                "year", vehicle.getYear(),
                "registration_number", vehicle.getRegistration_number(),
                "passenger_capacity", vehicle.getPassenger_capacity(),
                "luggage_capacity", vehicle.getLuggage_capacity(),
                "price_per_km", vehicle.getPrice_per_km(),
                "status", vehicle.getStatus()
        );
        ApiClient.post(url, requestBody);
    }

    public static void deleteVehicleById(int id) throws IOException {
        String url = Config.API_URL_BASE + "vehicle";
        Map<String, Object> requestBody = Map.of("id", id);
        ApiClient.delete(url, requestBody);
    }

    public static Vehicle getVehicleById(int id) throws IOException {
        String url = Config.API_URL_BASE + "vehicle?id=" + id;
        String apiResponse = ApiClient.get(url);
        return new Gson().fromJson(apiResponse, Vehicle.class);
    }

    public static List<Vehicle> getAllVehicles() throws IOException {
        String url = Config.API_URL_BASE + "vehicles";
        String apiResponse = ApiClient.get(url);
        Type vehicleListType = new TypeToken<List<Vehicle>>() {
        }.getType();
        return new Gson().fromJson(apiResponse, vehicleListType);
    }

}
