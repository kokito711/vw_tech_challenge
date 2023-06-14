package com.techtest.app.domain.values;

import lombok.Getter;

@Getter
public enum Orientation {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private String orientation;

    Orientation(String orientation) {
        this.orientation = orientation;
    }
}
