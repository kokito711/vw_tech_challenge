package com.techtest.app.infrastructure;

import com.techtest.app.RobotManagementApplication;
import com.techtest.app.application.request.RobotManagementRequestParser;
import com.techtest.app.domain.ports.RobotManagementPort;
import com.techtest.app.domain.services.RobotManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = RobotManagementApplication.class)
public class BeanConfiguration {
    @Bean
    RobotManagementPort robotManagement() {
        return new RobotManagement();
    }

    @Bean
    RobotManagementRequestParser robotManagementRequestParser() {
        return new RobotManagementRequestParser();
    }
}
