package com.example.Springboot.Learning.service;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import com.example.Springboot.Learning.exceptions.EmployeeNotFoundException;
import com.example.Springboot.Learning.model.Employee;
import com.example.Springboot.Learning.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//	@Autowired
//    private EmployeeRepository repo;

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmailService emailService;

    private static Integer counter;

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
        employee.setEmail(e.getEmail());
        Employee savedEmp = repo.save(employee);
//        Send Welcome Email
        logger.info("Sending the welcome email to new created employee: "+employee.getName());
        emailService.sendWelcomeEmail(savedEmp.getEmail(), savedEmp.getName());
        return savedEmp;
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

    public  Page<Employee> getAllEmployees(Pageable pageable){
        logger.info("Fetching all employees for name searching");
        return repo.findAll(pageable);
    }
}