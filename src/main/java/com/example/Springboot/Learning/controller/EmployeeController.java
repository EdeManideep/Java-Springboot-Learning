package com.example.Springboot.Learning.controller;

import com.example.Springboot.Learning.dto.EmployeeRegisterDTO;
import com.example.Springboot.Learning.model.Employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Controller",
        description = "Handles all operations related to Employee management such as creating, fetching, updating, and deleting employees."
)
@RestController
@RequestMapping("/api/employees")
public interface EmployeeController {
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size);

    @GetMapping("/{id}")

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid EmployeeRegisterDTO employee);

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody @Valid EmployeeRegisterDTO employee);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id);

    @GetMapping("searchByName")
    public ResponseEntity<?>  searchByName(@RequestParam String name,
                                           @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer pageSize);
}
