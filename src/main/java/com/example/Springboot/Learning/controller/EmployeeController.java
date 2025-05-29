package com.example.Springboot.Learning.controller;

import java.util.List;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import com.example.Springboot.Learning.model.Employee;
import com.example.Springboot.Learning.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Controller",
        description = "Handles all operations related to Employee management such as creating, fetching, updating, and deleting employees."
)
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);


    @Operation(
            summary = "Get all employees",
            description = "Returns a list of all employees with optional sorting by column and direction"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        logger.info("Fetching all employees");

        // Validate direction
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        List<Employee> employees = service.getAll(sort);

        if (employees.isEmpty()) {
            logger.warn("No employees present");
            return ResponseEntity.ok("No Employees Present");
        }

        return ResponseEntity.ok(employees);
    }


    @Operation(summary = "Get employee by ID", description = "Fetch a single employee using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "204", description = "Employee not there with given id (No Content)")
    })
    @GetMapping("/{id}")
    public Employee getById(
            @Parameter(description = "ID of the employee to be fetched") @PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(
            summary = "Create a new employee",
            description = "Adds a new employee using the provided data"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
           @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Employee> create(
            @Parameter(description = "Employee data for creation")
            @RequestBody EmployeeRegisterDTO employee) {
        Employee created = service.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @Operation(summary = "Update an existing employee", description = "Updates employee details based on the provided ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "204", description = "Employee not there with given id to update (No Content)")
    })
    @PutMapping("/{id}")
    public Employee update(
            @Parameter(description = "ID of the employee to update") @PathVariable Long id,
            @Parameter(description = "Updated employee data") @RequestBody EmployeeRegisterDTO employee) {
        return service.update(id, employee);
    }

    @Operation(summary = "Delete employee by ID", description = "Removes an employee from the system using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "204", description = "Employee not there with given id to delete (No Content)")
    })
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID of the employee to delete") @PathVariable Long id) {
        service.delete(id);
    }
}
