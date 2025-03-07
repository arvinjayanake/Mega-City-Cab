package com.arvin.megacitycab.model.enums;

public enum BookingStatus {
    PENDING(1, "Pending"),
    APPROVED(2, "Approved"),
    REJECTED(3, "Rejected"),
    STARTED(4, "Started"),
    COMPLETED(5, "Completed"),
    CANCELLED(6, "Cancelled");

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
