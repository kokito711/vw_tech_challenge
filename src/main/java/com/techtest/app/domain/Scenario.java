package com.techtest.app.domain;

import com.techtest.app.domain.exceptions.ScenarioException;
import com.techtest.app.domain.values.Instruction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Scenario {

    private final Pair<Integer, Integer> workplace;

    private final Position initialPosition;

    private final List<Instruction> instructions;

    public Scenario(@NonNull Integer length, @NonNull Integer width, String initialPosition, List<Character> instructions) {
        this.workplace = Pair.of(length, width);
        this.initialPosition = Position.fromString(initialPosition);
        this.instructions = instructions.stream().map(Instruction::fromValue).collect(Collectors.toList());
    }

    public void validateInitialPositionValidForWorkplace() {
        if (initialPosition.getPositionX() > workplace.getLeft()
                || initialPosition.getPositionY() > workplace.getRight()) {
            throw new ScenarioException("Initial position cannot be placed out of the workplace size");
        }
    }
}
