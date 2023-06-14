package com.techtest.app.domain;

import com.techtest.app.domain.exceptions.RobotException;
import com.techtest.app.domain.values.Orientation;
import com.techtest.app.domain.values.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class Robot {

    private final Position position;
    private final Status status;

    public Robot() {
        this.status = Status.WAITING_INSTRUCTIONS;
        this.position = new Position(0, 0, Orientation.NORTH);
    }

    public Robot(Position position) {
        this.status = Status.WAITING_INSTRUCTIONS;
        this.position = position;
    }

    public void move() {
        if (status.equals(Status.WAITING_INSTRUCTIONS)) {
            switch (position.getOrientation()) {
                case NORTH -> position.increaseY();
                case EAST -> position.increaseX();
                case SOUTH -> position.decreaseY();
                case WEST -> position.decreaseX();
            }
        } else {
            throw new RobotException("Robot is not cleaning right now");
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
}
