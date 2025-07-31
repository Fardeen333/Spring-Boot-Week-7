package com.example.condingshuttle.TestingApplication.services.impl;

import com.example.condingshuttle.TestingApplication.TestContainerConfig;
import com.example.condingshuttle.TestingApplication.dto.EmployeeDto;
import com.example.condingshuttle.TestingApplication.entities.Employee;
import com.example.condingshuttle.TestingApplication.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfig.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;

    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
        mockEmployee = Employee.builder()
                .id(1L)
                .name("fardeen")
                .email("fardeen@gmail.com")
                .salary(1000L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIdIsPresent_thenReturnEmployeeDto(){
        //assign

        Long id = mockEmployee.getId();
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee)); // STUBBING
        // act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        // assert
        Assertions.assertThat(employeeDto.getId()).isEqualTo(id);
        Assertions.assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        Mockito.verify(employeeRepository, Mockito.only()).findById(id);
    }

    @Test
    void testCreateNewEmployee_whenValidEmployee_thenCreateNewEmployee(){
        // assign
        Mockito.when(employeeRepository.findByEmail(Mockito.anyString())).thenReturn(List.of());
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(mockEmployee);
        // act

        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);
        // assert
        Assertions.assertThat(employeeDto).isNotNull();
        Assertions.assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());
        Mockito.verify(employeeRepository).save(Mockito.any(Employee.class));

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        Mockito.verify(employeeRepository).save(employeeArgumentCaptor.getValue());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        Assertions.assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }


}