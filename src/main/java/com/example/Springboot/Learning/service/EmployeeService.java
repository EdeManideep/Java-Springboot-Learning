package com.example.Springboot.Learning.service;

import java.util.List;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.Springboot.Learning.model.Employee;

@Service
public interface EmployeeService {
    public Page<Employee> getAll(Pageable pageable);
    public Employee getById(Long id);
    public Employee create(EmployeeRegisterDTO e);
    public Employee update(Long id, EmployeeRegisterDTO employee) ;
    public void delete(Long id);
    public  Page<Employee> getAllEmployees(Pageable pageable);
}