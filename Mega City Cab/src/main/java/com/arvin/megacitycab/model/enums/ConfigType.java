package com.arvin.megacitycab.model.enums;

public enum ConfigType {
    DOUBLE("double"),
    INT( "int"),
    STRING("string");
    private final String name;

    ConfigType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ConfigType fromString(String name) {
        for (ConfigType type : ConfigType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid config type name: " + name);
    }
}
