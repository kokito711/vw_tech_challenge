package com.techtest.app.domain;

import com.techtest.app.domain.exceptions.PositionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @ParameterizedTest
    @CsvSource({"5 0 Q", "P 6 N", "0 P N", "-6 0 N", "0 -1 N"})
    void cannotCreatePositionWhenInitialPositionAreNegativeOrInvalid(String initialPosition) {
        assertThatThrownBy(() -> Position.fromString(initialPosition))
                .isInstanceOf(PositionException.class)
                .hasMessage("Initial position cannot contain more than 3 chars in which the first 2 chars" +
                        " must be positive numbers and the third must be one of N,W,E,S");
    }
}