package com.techtest.app.domain;

import com.techtest.app.domain.values.Orientation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.techtest.app.domain.values.Orientation.EAST;
import static com.techtest.app.domain.values.Orientation.NORTH;
import static com.techtest.app.domain.values.Orientation.SOUTH;
import static com.techtest.app.domain.values.Orientation.WEST;

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
