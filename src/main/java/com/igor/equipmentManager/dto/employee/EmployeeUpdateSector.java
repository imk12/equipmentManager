package com.igor.equipmentManager.dto.employee;

import com.igor.equipmentManager.enums.Sector;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record EmployeeUpdateSector(@NotNull Sector sector) {
}
