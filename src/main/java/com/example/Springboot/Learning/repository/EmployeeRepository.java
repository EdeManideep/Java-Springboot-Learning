package com.example.Springboot.Learning.repository;

import com.example.Springboot.Learning.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCreatedAtAfter(LocalDateTime dateTime);
}