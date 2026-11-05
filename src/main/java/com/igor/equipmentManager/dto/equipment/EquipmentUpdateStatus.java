package com.igor.equipmentManager.dto.equipment;

import com.igor.equipmentManager.enums.EquipmentStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;



@Validated
public record EquipmentUpdateStatus(@NotNull EquipmentStatus equipmentStatus) {
}
