package com.techtest.app.domain.values;

import lombok.Getter;

@Getter
public enum Orientation {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String value;

    Orientation(String orientation) {
        this.value = orientation;
    }

    public static Orientation fromValue(String value) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.getValue().equalsIgnoreCase(value)) {
                return orientation;
            }
        }
        throw new EnumConstantNotPresentException(Orientation.class, "Orientation value not found");
    }

}
