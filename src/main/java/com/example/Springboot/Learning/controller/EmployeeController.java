package com.example.Springboot.Learning.controller;

import com.example.Springboot.Learning.constants.EmployeeApiConstants;
import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import com.example.Springboot.Learning.model.Employee;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/employees")
public interface EmployeeController {
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
            @RequestParam(defaultValue = "10") Integer size);

    @GetMapping("/{id}")
    public Employee getById(
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_ID_DESC) @PathVariable String id);

    @PostMapping
    public ResponseEntity<Employee> create(@Parameter(description = EmployeeApiConstants.PARAM_CREATE_EMPLOYEE_DESC)
                                               @RequestBody @Valid EmployeeRegisterDTO employee) ;

    @PutMapping("/{id}")
    public Employee update(@Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_ID_UPDATE_DESC) @PathVariable String id,
                           @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_UPDATE_DATA_DESC) @RequestBody @Valid EmployeeRegisterDTO employee);

    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_ID_DELETE_DESC) @PathVariable String id);

    @GetMapping("searchByName")
    public ResponseEntity<?>  searchByName(
            @Parameter(description = EmployeeApiConstants.PARAM_EMPLOYEE_NAME_SEARCH_DESC) @RequestParam String name,
            @Min(value = 0, message = EmployeeApiConstants.PARAM_PAGE_NUMBER_MIN)
            @Parameter(description = EmployeeApiConstants.PARAM_PAGE_NUMBER_DESC) @RequestParam(defaultValue = "0") Integer page,
            @Min(value = 1, message = EmployeeApiConstants.PARAM_PAGE_NUMBER_MIN_SIZE)
            @Parameter(description = EmployeeApiConstants.PARAM_PAGE_SIZE_DESC) @RequestParam(defaultValue = "10") Integer pageSize);
}