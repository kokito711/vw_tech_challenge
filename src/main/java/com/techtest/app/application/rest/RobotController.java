package com.techtest.app.application.rest;

import com.techtest.app.application.request.RobotManagementRequestParser;
import com.techtest.app.domain.ports.RobotManagementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RobotController {

    private final RobotManagementPort robotManagement;
    private final RobotManagementRequestParser robotManagementRequestParser;

    @PostMapping(consumes = TEXT_PLAIN_VALUE, produces = TEXT_PLAIN_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public String executeScenarios(@RequestBody String receivedInput) {
        return robotManagement.executeScenarios(robotManagementRequestParser.parseIncomingString(receivedInput));
    }
}
