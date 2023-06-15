package com.techtest.app.application;

import com.techtest.app.domain.ports.RobotManagementPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {

    @Autowired
    private final RobotManagementPort robotManagement;

}
