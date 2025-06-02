//todo controller should be an interface
//todo all static strings should come from static file's - after completing all changes
package com.example.Springboot.Learning.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import com.example.Springboot.Learning.model.Employee;
import com.example.Springboot.Learning.model.PagedResponse;
import com.example.Springboot.Learning.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

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
            summary = "Get all employees with pagination",
            description = "Returns a paginated list of employees with optional sorting by column and direction"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "204", description = "Employees not there (No Content)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAll(
            @Parameter(description = "Column name to sort by") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction: asc or desc") @RequestParam(defaultValue = "asc") String direction,
            @Parameter(description = "Page number (zero-based index)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of records per page") @RequestParam(defaultValue = "10") int size) {

        logger.info("Fetching all employees - page {}, size {}, sort by {}, direction {}", page, size, sortBy, direction);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeesPage = service.getAll(pageable);

        if (employeesPage.isEmpty()) {
            logger.warn("No employees found for the given page/filters.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Employees Present");
        }

        PagedResponse<Employee> response = new PagedResponse<>(
                employeesPage.getContent(),
                employeesPage.getNumber(),
                employeesPage.getSize(),
                employeesPage.getTotalElements(),
                employeesPage.getTotalPages(),
                employeesPage.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get employee by ID", description = "Fetch a single employee using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "204", description = "Employee not there with given id (No Content)"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    @GetMapping("/{id}")
    public Employee getById(
            @Parameter(description = "ID of the employee to be fetched") @PathVariable String id) {
        Long employeeId;
        try{
            employeeId = Long.parseLong(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be a valid integer value");
        }
        return service.getById(employeeId);
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
            @RequestBody @Valid EmployeeRegisterDTO employee) {
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
            @Parameter(description = "ID of the employee to update") @PathVariable String id,
            @Parameter(description = "Updated employee data") @RequestBody @Valid EmployeeRegisterDTO employee) {
        Long employeeId;
        try{
            employeeId = Long.parseLong(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be a valid integer value");
        }
        return service.update(employeeId, employee);
    }

    @Operation(summary = "Delete employee by ID", description = "Removes an employee from the system using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "204", description = "Employee not there with given id to delete (No Content)")
    })
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID of the employee to delete") @PathVariable String id) {
        Long employeeId;
        try{
            employeeId = Long.parseLong(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be a valid integer value");
        }
        service.delete(employeeId);
    }

    @Operation(
            summary = "Search employees by name",
            description = "Returns a list of employees whose names match the given keyword (case-insensitive)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved matching employees"),
            @ApiResponse(responseCode = "404", description = "No employees found with the given name")
    })
    @GetMapping("searchByName")
    public ResponseEntity<?>  searchByName(
            @Parameter(description = "Name to search")
            @RequestParam String name){
        logger.info("Searching employees with name containing: {}", name);
        List<Employee> employees = service.getAllEmployees();
        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    if(filteredEmployees.isEmpty()){
        logger.warn("No employees found matching name: {}", name);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No matching employees found");
    }
    return ResponseEntity.ok(filteredEmployees);
    }
}
