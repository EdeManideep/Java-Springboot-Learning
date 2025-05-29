package com.example.Springboot.Learning.service;

import java.util.List;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Springboot.Learning.exceptions.EmployeeNotFoundException;
import com.example.Springboot.Learning.model.Employee;
import com.example.Springboot.Learning.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;

@Service
public class EmployeeService {

//	@Autowired
//    private EmployeeRepository repo;

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    public Page<Employee> getAll(Pageable pageable) {
        logger.info("Calling repository to fetch paginated employee list");
        return repo.findAll(pageable);
    }

    public Employee getById(Long id) {
        repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return repo.findById(id).orElse(null);
    }

    public Employee create(EmployeeRegisterDTO e) {
        logger.info("Creating employee: {}", e.getName());
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

    public  List<Employee> getAllEmployees(){
        logger.info("Fetching all employees for name searching");
        return repo.findAll();
    }
}