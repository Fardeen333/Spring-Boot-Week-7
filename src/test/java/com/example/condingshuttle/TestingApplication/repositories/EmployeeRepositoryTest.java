package com.example.condingshuttle.TestingApplication.repositories;

import com.example.condingshuttle.TestingApplication.TestContainerConfig;
import com.example.condingshuttle.TestingApplication.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfig.class)
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .name("Fardeen")
                .email("fardeen@gmail.com")
                .salary(1000L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsValid_thenReturnEmployeeList() {
        // Arrange (Given)
        employeeRepository.save(employee);
        // Act (When)
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());
        // Assert (Then)
        assertThat(employeeList).isNotNull().isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsInvalid_thenReturnEmptyEmployeeList(){
        // Arrange
        String email="fardeen@gmail.com";
        // Act
        List<Employee> employeeList = employeeRepository.findByEmail(email);
        //Assert (Then)
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();
    }
}