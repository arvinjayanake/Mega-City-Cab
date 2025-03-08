package com.arvin.megacitycab.model.enums;

public enum ConfigKey {
    TAX("tax");
    private final String name;

    ConfigKey(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ConfigKey fromString(String name) {
        for (ConfigKey type : ConfigKey.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid config type name: " + name);
    }
}
