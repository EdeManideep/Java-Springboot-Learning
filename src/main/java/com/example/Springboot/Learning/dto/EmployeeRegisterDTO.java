package com.example.Springboot.Learning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRegisterDTO {
    @Schema(description = "Full name of the employee", example = "Manideep")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Department where the employee works", example = "HR")
    @NotBlank(message = "Department is required")
    private String department;

    @Schema(description = "Monthly salary of the employee", example = "4500.75")
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private Double salary;

    @Schema(description = "Email address of the employee", example = "manideep@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
