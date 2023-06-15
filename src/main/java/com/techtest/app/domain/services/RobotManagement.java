package com.techtest.app.domain.services;

import com.techtest.app.domain.Robot;
import com.techtest.app.domain.Scenario;
import com.techtest.app.domain.ports.RobotManagementPort;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.LF;

public class RobotManagement implements RobotManagementPort {

    private static void appendLastPositionToResults(StringBuilder responseBuilder, String currentRobotPosition) {
        if (responseBuilder.length() > 0 && responseBuilder.length() % 5 == 0) {
            responseBuilder.append(LF);
        }
        responseBuilder.append(currentRobotPosition);
    }

    @Override
    public String executeScenarios(List<Scenario> scenarios) {
        StringBuilder responseBuilder = new StringBuilder();
        scenarios.forEach(scenario -> {
            scenario.validateInitialPositionValidForWorkplace();
            var robot = new Robot(scenario.getWorkplace(), scenario.getInitialPosition());
            scenario.getInstructions().forEach(instruction -> {
                switch (instruction) {
                    case M -> robot.move();
                    case L -> robot.rotateLeft();
                    case R -> robot.rotateRight();
                }
            });
            appendLastPositionToResults(responseBuilder, robot.getCurrentPositionAsString());
        });
        return responseBuilder.length() > 0 ? responseBuilder.toString() : Strings.EMPTY;
    }
}
