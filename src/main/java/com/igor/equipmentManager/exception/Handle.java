package com.igor.equipmentManager.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handle {
    @ExceptionHandler(AlreadyHasAssetTagException.class)
    public ResponseEntity<String> alreadyHasAssetTagException(AlreadyHasAssetTagException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(AlreadyHasEmailException.class)
    public ResponseEntity<String> alreadyHasEmailException(AlreadyHasEmailException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<String> equipmentNotFoundException(EquipmentNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<String> loanNotFoundException(LoanNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
