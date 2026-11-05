package com.igor.equipmentManager.dto.employee;

import com.igor.equipmentManager.enums.Sector;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record EmployeeCreation(@NotNull Sector sector,
                               @NotBlank @Email String email,
                               @NotBlank String name) {
}
