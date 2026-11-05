package com.igor.equipmentManager.dto.loan;

import com.igor.equipmentManager.entity.Employee;
import com.igor.equipmentManager.entity.Equipment;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record LoanCreation(@NotNull Employee employee, @NotNull Equipment equipment) {
}
