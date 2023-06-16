package com.techtest.app.application.rest;

import com.techtest.app.application.response.ErrorResponse;
import com.techtest.app.domain.exceptions.PositionException;
import com.techtest.app.domain.exceptions.RobotException;
import com.techtest.app.domain.exceptions.ScenarioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RobotControllerAdvice {

    @ExceptionHandler({
            RobotException.class,
            EnumConstantNotPresentException.class,
            PositionException.class,
            ScenarioException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleErrorNotFound(RuntimeException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
