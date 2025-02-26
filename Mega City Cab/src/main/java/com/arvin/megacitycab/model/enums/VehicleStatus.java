package com.arvin.megacitycab.model.enums;

public enum VehicleStatus {
    DEFAULT(0, "Default"),
    AVAILABLE(1, "Available"),
    NOT_AVAILABLE(2, "Not Available"),
    MAINTENANCE(3, "Maintenance");

    private final int value;
    private final String name;

    VehicleStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }

    public static boolean isValid(int value){
        for (VehicleStatus type : VehicleStatus.values()) {
            if (type.value == value) {
                return true;
            }
        }
        return false;
    }

    public static VehicleStatus fromInt(int value) {
        for (VehicleStatus type : VehicleStatus.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle status value: " + value);
    }

    public static VehicleStatus fromString(String name) {
        for (VehicleStatus type : VehicleStatus.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle status name: " + name);
    }
}
