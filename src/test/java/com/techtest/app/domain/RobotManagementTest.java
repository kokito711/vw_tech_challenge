package com.techtest.app.domain;

import com.techtest.app.domain.services.RobotManagement;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotManagementTest {

    private final RobotManagement robotManagement = new RobotManagement();

    public static Stream<Arguments> scenarios() {
        return Stream.of(
                Arguments.of(
                        List.of(new Scenario(5, 5, "1 2 N", List.of('L', 'M', 'L', 'M', 'L', 'M', 'L', 'M', 'M'))),
                        "1 3 N"
                ),
                Arguments.of(
                        List.of(new Scenario(5, 5, "3 3 E", List.of('M', 'M', 'R', 'M', 'M', 'R', 'M', 'R', 'R', 'M'))),
                        "5 1 E"),
                Arguments.of(List.of(
                                new Scenario(5, 5, "1 2 N", List.of('L', 'M', 'L', 'M', 'L', 'M', 'L', 'M', 'M')),
                                new Scenario(5, 5, "3 3 E", List.of('M', 'M', 'R', 'M', 'M', 'R', 'M', 'R', 'R', 'M'))),
                        "1 3 N\n5 1 E")
        );
    }

    @Test
    void shouldReturnEmptyWhenNoScenariosAvailable() {
        List<Scenario> scenarios = Collections.emptyList();

        var response = robotManagement.executeScenarios(scenarios);

        assertThat(response).isEqualTo(Strings.EMPTY);
    }

    @ParameterizedTest
    @MethodSource("scenarios")
    void shouldProcessAndExecuteScenario(List<Scenario> scenarios, String expectedResponse) {
        var response = robotManagement.executeScenarios(scenarios);

        assertThat(response).isEqualTo(expectedResponse);
    }
}
