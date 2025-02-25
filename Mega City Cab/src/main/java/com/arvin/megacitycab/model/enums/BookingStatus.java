package com.arvin.megacitycab.model.enums;

public enum BookingStatus {
    PENDING(1, "Pending"),
    CONFIRMED(2, "Confirmed"),
    STARTED(2, "Started"),
    COMPLETED(3, "Completed"),
    CANCELLED(4, "Cancelled");

    private final int value;
    private final String name;

    BookingStatus(int value, String name) {
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

    public static BookingStatus fromInt(int value) {
        for (BookingStatus type : BookingStatus.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid booking status value: " + value);
    }

    public static BookingStatus fromString(String name) {
        for (BookingStatus type : BookingStatus.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid booking status name: " + name);
    }
}
