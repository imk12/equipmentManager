package com.igor.equipmentManager.exception;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException(String type, Long value) {
        super("Equipamento com o " + type + " " + value + " não foi encontrado");
    }
}
