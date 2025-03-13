package com.arvin.megacitycab.model.enums;

public enum PaymentType {
    PAYMENT(0, "Payment"),
    REFUND(1, "Refund");

    private final int value;
    private final String name;

    PaymentType(int value, String name) {
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

    public static PaymentType fromInt(int value) {
        for (PaymentType type : PaymentType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid payment type value: " + value);
    }

    public static PaymentType fromString(String name) {
        for (PaymentType type : PaymentType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid payment type name: " + name);
    }
}
