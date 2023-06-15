package com.techtest.app.domain;

import com.techtest.app.domain.exceptions.PositionException;
import com.techtest.app.domain.values.Orientation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.techtest.app.domain.values.Orientation.EAST;
import static com.techtest.app.domain.values.Orientation.NORTH;
import static com.techtest.app.domain.values.Orientation.SOUTH;
import static com.techtest.app.domain.values.Orientation.WEST;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Getter
@EqualsAndHashCode
@ToString
public class Position {
    private int positionX;
    private int positionY;

    private Orientation orientation;

    public Position(int positionX, int positionY, Orientation orientation) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

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

    public void increaseY() {
        positionY++;
    }

    public void decreaseY() {
        if (positionY > 0) {
            positionY--;
        }
    }

    public void increaseX() {
        positionX++;
    }

    public void decreaseX() {
        if (positionX > 0) {
            positionX--;
        }
    }

    public void orientateWest() {
        orientation = WEST;
    }

    public void orientateSouth() {
        orientation = SOUTH;
    }

    public void orientateEast() {
        orientation = EAST;
    }

    public void orientateNorth() {
        orientation = NORTH;
    }
}
