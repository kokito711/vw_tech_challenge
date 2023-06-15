package com.techtest.app.infrastructure;

import com.techtest.app.AppApplication;
import com.techtest.app.domain.ports.RobotManagementPort;
import com.techtest.app.domain.services.RobotManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppApplication.class)
public class BeanConfiguration {
    @Bean
    RobotManagementPort robotManagement() {
        return new RobotManagement();
    }
}
