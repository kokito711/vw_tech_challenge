package com.techtest.app.domain;


import com.techtest.app.domain.values.Orientation;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.techtest.app.Constants.POSITION_0_0_E;
import static com.techtest.app.Constants.POSITION_0_0_N;
import static com.techtest.app.Constants.POSITION_0_0_S;
import static com.techtest.app.Constants.POSITION_0_0_W;
import static com.techtest.app.Constants.WORKPLACE_SIZE;
import static com.techtest.app.domain.values.Orientation.EAST;
import static com.techtest.app.domain.values.Orientation.NORTH;
import static com.techtest.app.domain.values.Orientation.SOUTH;
import static com.techtest.app.domain.values.Orientation.WEST;
import static org.assertj.core.api.Assertions.assertThat;

public class RobotTest {

    private static Stream<Arguments> movePositionsPositive() {
        return Stream.of(
                Arguments.of(POSITION_0_0_N, new Position(0, 1, NORTH)),
                Arguments.of(new Position(0, 1, SOUTH), POSITION_0_0_S),
                Arguments.of(new Position(1, 0, WEST), POSITION_0_0_W),
                Arguments.of(POSITION_0_0_E, new Position(1, 0, EAST)));
    }

    private static Stream<Arguments> rotateRobotLeft() {
        return Stream.of(
                Arguments.of(POSITION_0_0_N, WEST),
                Arguments.of(POSITION_0_0_W, SOUTH),
                Arguments.of(POSITION_0_0_S, EAST),
                Arguments.of(POSITION_0_0_E, NORTH));
    }

    @ParameterizedTest
    @MethodSource("movePositionsPositive")
    void shouldMoveWhenNextPositionIsInsideTheMatrix(Position initialPosition, Position expectedPosition) {
        var robot = new Robot(WORKPLACE_SIZE, initialPosition);

        robot.move();

        assertThat(robot.getPosition()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @EnumSource(Orientation.class)
    void shouldNotMoveWhenDestinationPositionIsOutOfBounds(Orientation orientation) {
        var robot = new Robot(Pair.of(0, 0), new Position(0, 0, orientation));

        robot.move();

        assertThat(robot.getPosition().getPositionX()).isEqualTo(0);
        assertThat(robot.getPosition().getPositionY()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("rotateRobotLeft")
    void shouldRotateToTheLeft(Position initialPosition, Orientation expectedOrientation) {
        var robot = new Robot(WORKPLACE_SIZE, initialPosition);

        robot.rotateLeft();

        assertThat(robot.getPosition().getOrientation()).isEqualTo(expectedOrientation);
    }

    @Test
        //This test is set like this due to a race condition if I set a Method source
    void shouldRotateToTheRight() {
        var robot = new Robot(WORKPLACE_SIZE);

        robot.rotateRight();
        assertThat(robot.getPosition().getOrientation()).isEqualTo(EAST);
        robot.rotateRight();
        assertThat(robot.getPosition().getOrientation()).isEqualTo(SOUTH);
        robot.rotateRight();
        assertThat(robot.getPosition().getOrientation()).isEqualTo(WEST);
        robot.rotateRight();
        assertThat(robot.getPosition().getOrientation()).isEqualTo(NORTH);
    }
}
