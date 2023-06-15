package com.techtest.app.domain.values;

import com.techtest.app.domain.exceptions.RobotException;
import lombok.Getter;

@Getter
public enum Instruction {
    M("M"),
    L("L"),
    R("R");

    private final String value;

    Instruction(String instruction) {
        this.value = instruction;
    }

    public static Instruction fromValue(Character value) {
        for (var instruction : Instruction.values()) {
            if (instruction.getValue().equalsIgnoreCase(String.valueOf(value))) {
                return instruction;
            }
        }
        throw new RobotException("Instruction not valid");
    }
}
