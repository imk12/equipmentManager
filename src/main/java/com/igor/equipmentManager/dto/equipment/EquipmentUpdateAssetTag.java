package com.igor.equipmentManager.dto.equipment;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record EquipmentUpdateAssetTag(@NotBlank String assetTag) {
}
