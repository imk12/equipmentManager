package com.igor.equipmentManager.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String type, Long id) {
        super("Loan com o " + type + " " + id + " não encontrado");
    }
}
