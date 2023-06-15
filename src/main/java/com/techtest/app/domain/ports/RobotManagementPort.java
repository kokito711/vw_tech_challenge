package com.techtest.app.domain.ports;

import com.techtest.app.domain.Scenario;

import java.util.List;

public interface RobotManagementPort {

    String executeScenarios(List<Scenario> scenario);
}
