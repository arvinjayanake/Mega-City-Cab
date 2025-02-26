package com.arvin.megacitycab.model.enums;

public enum VehicleCategory {
    UNCATEGORIZED(0, "Uncategorized"),
    SEDAN(1, "Sedan"),
    SUV(2, "SUV"),
    VAN(3, "Van"),
    HATCHBACK(4, "Hatchback"),
    ELECTRIC(5, "Electric");

    private final int value;
    private final String name;

    VehicleCategory(int value, String name) {
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
        for (VehicleCategory type : VehicleCategory.values()) {
            if (type.value == value) {
                return true;
            }
        }
        return false;
    }


    public static VehicleCategory fromInt(int value) {
        for (VehicleCategory type : VehicleCategory.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle category value: " + value);
    }

    public static VehicleCategory fromString(String name) {
        for (VehicleCategory type : VehicleCategory.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle category name: " + name);
    }
}
