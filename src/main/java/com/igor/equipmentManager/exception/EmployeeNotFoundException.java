package com.igor.equipmentManager.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String type, Long value) {
        super("Funcionário com o " + type + " " + value + " não foi encontrado");
    }
}
