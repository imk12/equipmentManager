package com.igor.equipmentManager.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record EmployeeUpdateEmail(@NotBlank @Email String email) {
}
