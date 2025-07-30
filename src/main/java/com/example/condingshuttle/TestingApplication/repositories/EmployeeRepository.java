package com.example.condingshuttle.TestingApplication.repositories;

import com.example.condingshuttle.TestingApplication.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String email);
}
