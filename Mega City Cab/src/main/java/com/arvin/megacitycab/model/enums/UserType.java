package com.arvin.megacitycab.model.enums;

public enum UserType {
    CUSTOMER(1, "Customer"),
    DRIVER(2, "Driver"),
    ADMIN(3, "Admin");

    private final int value;
    private final String name;

    UserType(int value, String name) {
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

    public static UserType fromInt(int value) {
        for (UserType type : UserType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid user type value: " + value);
    }

    public static UserType fromString(String name) {
        for (UserType type : UserType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid user type name: " + name);
    }
}
