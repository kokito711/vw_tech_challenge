package com.techtest.app.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;

import static com.techtest.app.domain.values.Orientation.NORTH;
import static org.apache.commons.lang3.StringUtils.SPACE;

@EqualsAndHashCode
@ToString
@Getter
public class Robot {

    private final Position position;
    private final Pair<Integer, Integer> workplaceSize;

    public Robot(Pair<Integer, Integer> workplaceSize, Position initialPosition) {
        this.position = initialPosition;
        this.workplaceSize = workplaceSize;
    }

    public Robot(Pair<Integer, Integer> workplaceSize) {
        this.position = new Position(0, 0, NORTH);
        this.workplaceSize = workplaceSize;
    }

    public void move() {
        switch (position.getOrientation()) {
            case NORTH -> {
                if (position.getPositionY() + 1 < workplaceSize.getRight()) {
                    position.increaseY();
                }
            }
            case EAST -> {
                if (position.getPositionY() + 1 < workplaceSize.getRight()) {
                    position.increaseX();
                }
            }
            case SOUTH -> position.decreaseY();
            case WEST -> position.decreaseX();
        }
    }

    public void rotateLeft() {
        switch (position.getOrientation()) {
            case NORTH -> position.orientateWest();
            case WEST -> position.orientateSouth();
            case SOUTH -> position.orientateEast();
            case EAST -> position.orientateNorth();
        }
    }

    public void rotateRight() {
        switch (position.getOrientation()) {
            case NORTH -> position.orientateEast();
            case WEST -> position.orientateNorth();
            case SOUTH -> position.orientateWest();
            case EAST -> position.orientateSouth();
        }
    }

    public String getCurrentPositionAsString() {
        return position.getPositionX() +
                SPACE +
                position.getPositionY() +
                SPACE +
                position.getOrientation().getValue();
    }
}
