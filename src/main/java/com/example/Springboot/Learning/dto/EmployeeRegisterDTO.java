package com.example.Springboot.Learning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmployeeRegisterDTO {
    @Schema(description = "Full name of the employee", example = "Manideep")
    private String name;

    @Schema(description = "Department where the employee works", example = "HR")
    private String department;

    @Schema(description = "Monthly salary of the employee", example = "4500.75")
    private Double salary;
}
