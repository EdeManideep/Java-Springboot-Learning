package com.example.Springboot.Learning.repository;

import com.example.Springboot.Learning.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}