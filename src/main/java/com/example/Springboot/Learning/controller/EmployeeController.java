//todo controller should be an interface
//todo all static strings should come from static file's - after completing all changes
package com.example.Springboot.Learning.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Springboot.Learning.constants.EmployeeApiConstants;
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
import jakarta.validation.constraints.Min;
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
            summary = EmployeeApiConstants.GET_ALL_EMPLOYEES_SUMMARY,
            description = EmployeeApiConstants.GET_ALL_EMPLOYEES_DESCRIPTION
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = EmployeeApiConstants.RESPONSE_EMPLOYEES_LIST_SUCCESS),
            @ApiResponse(responseCode = "204", description = EmployeeApiConstants.RESPONSE_EMPLOYEES_NOT_FOUND),
            @ApiResponse(responseCode = "500", description = EmployeeApiConstants.RESPONSE_INTERNAL_SERVER_ERROR)
    })
    @GetMapping
    public ResponseEntity<?> getAll(
            @Parameter(description = EmployeeApiConstants.PARAM_SORT_BY_DESC)
            @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = EmployeeApiConstants.PARAM_SORT_DIRECTION_DESC)
            @RequestParam(defaultValue = "asc") String direction,
            @Min(value = 0, message = EmployeeApiConstants.PARAM_PAGE_NUMBER_MIN)
            @Parameter(description = EmployeeApiConstants.PARAM_PAGE_NUMBER_DESC)
            @RequestParam(defaultValue = "0") Integer page,
            @Min(value = 1, message = EmployeeApiConstants.PARAM_PAGE_NUMBER_MIN_SIZE)
            @Parameter(description = EmployeeApiConstants.PARAM_PAGE_SIZE_DESC)
            @RequestParam(defaultValue = "10") Integer size) {

        logger.info(EmployeeApiConstants.LOG_EMPLOYEES_FETCHING, page, size, sortBy, direction);

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeesPage = service.getAll(pageable);

        if (employeesPage.isEmpty()) {
            logger.warn(EmployeeApiConstants.LOG_EMPLOYEES_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(EmployeeApiConstants.MESSAGE_NO_EMPLOYEES);
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

    @Operation(summary = EmployeeApiConstants.GET_EMPLOYEE_SUMMARY, description = EmployeeApiConstants.GET_EMPLOYEE_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_FOUND),
            @ApiResponse(responseCode = "204", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_NOT_FOUND),
            @ApiResponse(responseCode = "400", description = EmployeeApiConstants.RESPONSE_INVALID_ID)
    })
    @GetMapping("/{id}")
    public Employee getById(
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_ID_DESC) @PathVariable String id) {
        Long employeeId;
        try{
            employeeId = Long.parseLong(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EmployeeApiConstants.ERROR_INVALID_ID);
        }
        return service.getById(employeeId);
    }

    @Operation(
            summary = EmployeeApiConstants.CREATE_EMPLOYEE_SUMMARY,
            description = EmployeeApiConstants.CREATE_EMPLOYEE_DESCRIPTION
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_CREATED),
            @ApiResponse(responseCode = "400", description = EmployeeApiConstants.RESPONSE_INVALID_INPUT),
            @ApiResponse(responseCode = "500", description = EmployeeApiConstants.RESPONSE_INTERNAL_ERROR)
    })
    @PostMapping
    public ResponseEntity<Employee> create(
            @Parameter(description = EmployeeApiConstants.PARAM_CREATE_EMPLOYEE_DESC)
            @RequestBody @Valid EmployeeRegisterDTO employee) {
        System.out.println("Creating employee: " + employee);
        Employee created = service.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @Operation(summary = EmployeeApiConstants.UPDATE_EMPLOYEE_SUMMARY, description = EmployeeApiConstants.UPDATE_EMPLOYEE_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_UPDATED),
            @ApiResponse(responseCode = "204", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_NOT_FOUND_UPDATE),
            @ApiResponse(responseCode = "400", description = EmployeeApiConstants.RESPONSE_INVALID_ID)
    })
    @PutMapping("/{id}")
    public Employee update(
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_ID_UPDATE_DESC) @PathVariable String id,
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_UPDATE_DATA_DESC) @RequestBody @Valid EmployeeRegisterDTO employee) {
        Long employeeId;
        try{
            employeeId = Long.parseLong(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EmployeeApiConstants.ERROR_INVALID_ID);
        }
        return service.update(employeeId, employee);
    }

    @Operation(summary = EmployeeApiConstants.DELETE_EMPLOYEE_SUMMARY, description = EmployeeApiConstants.DELETE_EMPLOYEE_DESCRIPTION)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_DELETED),
            @ApiResponse(responseCode = "204", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_NOT_FOUND_DELETE),
            @ApiResponse(responseCode = "400", description = EmployeeApiConstants.RESPONSE_INVALID_ID)
    })
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_ID_DELETE_DESC) @PathVariable String id) {
        Long employeeId;
        try{
            employeeId = Long.parseLong(id);
        }catch (NumberFormatException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EmployeeApiConstants.ERROR_INVALID_ID);
        }
        service.delete(employeeId);
    }

    @Operation(
            summary = EmployeeApiConstants.SEARCH_EMPLOYEE_SUMMARY,
            description = EmployeeApiConstants.SEARCH_EMPLOYEE_DESCRIPTION
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_SEARCH_SUCCESS),
            @ApiResponse(responseCode = "204", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_NOT_FOUND_SEARCH_BY_NAME),
            @ApiResponse(responseCode = "404", description = EmployeeApiConstants.RESPONSE_EMPLOYEE_SEARCH_NOT_FOUND)
    })
    @GetMapping("searchByName")
    public ResponseEntity<?>  searchByName(
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_NAME_SEARCH_DESC) @RequestParam String name,
            @Min(value = 0, message = EmployeeApiConstants.PARAM_PAGE_NUMBER_MIN)
            @Parameter(description = EmployeeApiConstants.PARAM_PAGE_NUMBER_DESC) @RequestParam(defaultValue = "0") Integer page,
            @Min(value = 1, message = EmployeeApiConstants.PARAM_PAGE_NUMBER_MIN_SIZE)
            @Parameter(description = EmployeeApiConstants.PARAM_PAGE_SIZE_DESC) @RequestParam(defaultValue = "10") Integer pageSize)
    {
        logger.info(EmployeeApiConstants.LOG_EMPLOYEE_SEARCH, name);
//        List<Employee> employees = service.getAllEmployees();
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Employee> employeePage = service.getAllEmployees(pageable);

        if(employeePage.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(EmployeeApiConstants.MESSAGE_NO_EMPLOYEES);
        }

        List<Employee> filteredEmployees = employeePage.stream()
                .filter(emp -> emp.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    if(filteredEmployees.isEmpty()){
        logger.warn(EmployeeApiConstants.LOG_EMPLOYEE_SEARCH_NOT_FOUND, name);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EmployeeApiConstants.MESSAGE_EMPLOYEE_SEARCH_NOT_FOUND);
    }
    return ResponseEntity.ok(filteredEmployees);
    }
}
