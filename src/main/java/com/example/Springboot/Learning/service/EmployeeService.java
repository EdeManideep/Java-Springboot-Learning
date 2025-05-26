package com.example.Springboot.Learning.service;

import java.util.List;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import org.springframework.stereotype.Service;

import com.example.Springboot.Learning.exceptions.EmployeeNotFoundException;
import com.example.Springboot.Learning.model.Employee;
import com.example.Springboot.Learning.repository.EmployeeRepository;

@Service
public class EmployeeService {

//	@Autowired
//    private EmployeeRepository repo;

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return repo.findById(id).orElse(null);
    }

    public Employee create(EmployeeRegisterDTO e) {
        Employee employee = new Employee();
        employee.setName(e.getName());
        employee.setDepartment(e.getDepartment());
        employee.setSalary(e.getSalary());
        return repo.save(employee);
    }

    public Employee update(Long id, EmployeeRegisterDTO employee) {
        Employee employee1 = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        // Update fields explicitly
        employee1.setName(employee.getName());
        employee1.setDepartment(employee.getDepartment());
        employee1.setSalary(employee.getSalary());

        return repo.save(employee1);
    }

    public void delete(Long id) {
        repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        repo.deleteById(id);
    }
}