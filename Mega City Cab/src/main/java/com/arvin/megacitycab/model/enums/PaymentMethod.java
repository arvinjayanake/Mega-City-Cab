package com.arvin.megacitycab.model.enums;

public enum PaymentMethod {
    CASH(1, "Cash"),
    ONLINE(2, "Online");
    private final int value;
    private final String name;

    PaymentMethod(int value, String name) {
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

    public static PaymentMethod fromInt(int value) {
        for (PaymentMethod type : PaymentMethod.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid payment method value: " + value);
    }

    public static PaymentMethod fromString(String name) {
        for (PaymentMethod type : PaymentMethod.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid payment method name: " + name);
    }
}
