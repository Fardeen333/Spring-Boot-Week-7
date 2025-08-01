package com.example.condingshuttle.TestingApplication.controllers;

import com.example.condingshuttle.TestingApplication.TestContainerConfig;
import com.example.condingshuttle.TestingApplication.dto.EmployeeDto;
import com.example.condingshuttle.TestingApplication.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfig.class)
public class AbstractIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    Employee testEmployee = Employee.builder()
            .id(1L)
                .email("fardeen@gmail.com")
                .name("Fardeen")
                .salary(200L)
                .build();
    EmployeeDto testEmployeeDto = EmployeeDto.builder()
            .id(1L)
                .email("fardeen@gmail.com")
                .name("Fardeen")
                .salary(200L)
                .build();
}
