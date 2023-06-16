package com.techtest.app.application.request;

import com.techtest.app.domain.Scenario;
import com.techtest.app.domain.exceptions.RobotException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class RobotManagementRequestParser {

    public List<Scenario> parseIncomingString(String receivedInput) {
        List<Scenario> scenarios = new ArrayList<>();
        var orders = Arrays.asList(receivedInput.split("\n"));
        validateEnoughElements(orders);
        var workplaceSize = parseWorkplaceSize(orders.get(0));
        orders = orders.subList(1, orders.size());
        for (int i = 0; i < orders.size(); i = i + 2) {
            var movementInstructions = orders.get(i + 1)
                    .chars()
                    .mapToObj(character -> (char) character)
                    .collect(Collectors.toList());
            scenarios.add(new Scenario(
                    workplaceSize.get("length"),
                    workplaceSize.get("width"),
                    orders.get(i),
                    movementInstructions));
        }
        return scenarios;
    }

    private void validateEnoughElements(List<String> orders) {
        if (orders.size() < 3) {
            throwInvalidInstructionsException();
        }
        if (orders.size() > 3) {
            var instructions = orders.size() - 1;
            if (instructions % 2 != 0) {
                throwInvalidInstructionsException();
            }
        }
    }

    private Map<String, Integer> parseWorkplaceSize(String workplaceSize) {
        workplaceSize = workplaceSize.replace(" ", "");
        if (workplaceSize.length() != 2) {
            throw new RobotException("Invalid workplace size dimensions");
        }
        Integer length = null;
        Integer width = null;

        try {
            length = parseInt(valueOf(workplaceSize.charAt(0)));
            width = parseInt(valueOf(workplaceSize.charAt(1)));
        } catch (NumberFormatException exception) {
            throw new RobotException("Invalid workplace size dimensions");
        }
        return Map.of(
                "length", length,
                "width", width
        );
    }

    private void throwInvalidInstructionsException() {
        throw new RobotException("Error while introducing instructions. " +
                "An even number of instructions must be provided along with the size of the workplace");
    }
}
