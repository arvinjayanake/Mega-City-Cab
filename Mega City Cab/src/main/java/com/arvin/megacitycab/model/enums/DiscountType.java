package com.arvin.megacitycab.model.enums;

public enum DiscountType {
    FIXED_AMOUNT(0, "Fixed Amount"),
    PERCENTAGE(1, "Percentage");
    private final int value;
    private final String name;

    DiscountType(int value, String name) {
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

    public static DiscountType fromInt(int value) {
        for (DiscountType type : DiscountType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid discount type value: " + value);
    }

    public static DiscountType fromString(String name) {
        for (DiscountType type : DiscountType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid discount type name: " + name);
    }
}
