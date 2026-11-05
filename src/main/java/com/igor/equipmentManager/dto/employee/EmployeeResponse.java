package com.igor.equipmentManager.dto.employee;

import com.igor.equipmentManager.enums.Sector;

public record EmployeeResponse(Long id, String name, Sector sector) {
}
