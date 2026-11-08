package com.igor.equipmentManager.controller;

import com.igor.equipmentManager.dto.employee.EmployeeCreation;
import com.igor.equipmentManager.dto.employee.EmployeeResponse;
import com.igor.equipmentManager.dto.employee.EmployeeUpdateEmail;
import com.igor.equipmentManager.dto.employee.EmployeeUpdateSector;
import com.igor.equipmentManager.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class EmployeeController {

    EmployeeService service;

    @PostMapping("employee")
    public ResponseEntity<EmployeeResponse> registerEmployee(
            @RequestBody @Valid EmployeeCreation creation){
        return ResponseEntity.status(201).body(service.registerAnEmployee(creation));
    }

    @GetMapping("employee")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){
        return ResponseEntity.ok().body(service.getEmployees());
    }

    @GetMapping("employee/{Id}")
    public ResponseEntity<EmployeeResponse> getEmployee(
            @PathVariable @PositiveOrZero @NotNull Long Id){
        return ResponseEntity.ok().body(service.getEmployee(Id));
    }

    @PatchMapping("employee/{Id}/email")
    public ResponseEntity<Void> updateEmail(
            @PathVariable @PositiveOrZero @NotNull Long Id,
            @RequestBody @Valid EmployeeUpdateEmail employeeUpdateEmail){
        service.updateEmail(Id,employeeUpdateEmail);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("employee/{Id}/sector")
    public ResponseEntity<Void> updateSector(
            @PathVariable @PositiveOrZero @NotNull Long Id,
            @RequestBody @Valid EmployeeUpdateSector employeeUpdateSector){
        service.updateSector(Id,employeeUpdateSector);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("employee/{Id}")
    public ResponseEntity<Void> removeEmployee(
            @PathVariable @PositiveOrZero @NotNull Long Id){
        service.removeEmployee(Id);
        return ResponseEntity.ok().build();
    }

    EmployeeController(EmployeeService service){
        this.service = service;
    }
}
