package com.techtest.app.domain.ports;

import java.util.List;

public interface RobotManagementPort {
    void setWorkspaceDimensions(Integer length, Integer width);

    String splitInstructions(List<String> instructions);
}
