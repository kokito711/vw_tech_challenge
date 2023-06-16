package com.techtest.app.domain.factories;

import com.techtest.app.domain.Position;
import com.techtest.app.domain.exceptions.PositionException;
import com.techtest.app.domain.values.Orientation;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class PositionFactory {
    public static Position fromString(String position) {
        int positionX = 0;
        int positionY = 0;
        Orientation orientation = null;
        position = position.replace(" ", "").replace(",", "");
        var wrongInit = false;

        try {
            if (position.length() == 3) {
                positionX = parseInt(valueOf(position.charAt(0)));
                positionY = parseInt(valueOf(position.charAt(1)));
                orientation = Orientation.fromValue(valueOf(position.charAt(2)));
            } else {
                wrongInit = true;
            }
        } catch (NumberFormatException | EnumConstantNotPresentException exception) {
            wrongInit = true;
        }
        if (wrongInit) {
            throw new PositionException("Initial position cannot contain more than 3 chars in which the first 2 chars" +
                    " must be positive numbers and the third must be one of N,W,E,S");
        }
        return new Position(positionX, positionY, orientation);
    }
}
