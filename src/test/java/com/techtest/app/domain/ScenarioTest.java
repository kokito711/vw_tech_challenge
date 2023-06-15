package com.techtest.app.domain;

import com.techtest.app.domain.exceptions.ScenarioException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScenarioTest {

    @ParameterizedTest
    @CsvSource({"6 0 N", "0 6 N"})
    void cannotCreateRobotInInitialPositionGreaterThanWorkplaceSize(String initialPosition) {
        var scenario = new Scenario(5, 5, initialPosition, Collections.emptyList());
        assertThatThrownBy(scenario::validateInitialPositionValidForWorkplace)
                .isInstanceOf(ScenarioException.class)
                .hasMessage("Initial position cannot be placed out of the workplace size");
    }

}