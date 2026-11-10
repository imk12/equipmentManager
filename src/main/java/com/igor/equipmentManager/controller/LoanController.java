package com.igor.equipmentManager.controller;

import com.igor.equipmentManager.dto.loan.LoanCreation;
import com.igor.equipmentManager.dto.loan.LoanResponse;
import com.igor.equipmentManager.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class LoanController {
    LoanService service;

    @PostMapping("loans")
    public ResponseEntity<LoanResponse> createLoan(
            @RequestBody @Valid LoanCreation creation){
        return ResponseEntity.status(201).body(service.createLoan(creation));
    }

    @GetMapping("loans")
    public ResponseEntity<List<LoanResponse>> getLoans(){
        return ResponseEntity.ok().body(service.getLoans());
    }

    @GetMapping("loans/{Id}")
    public ResponseEntity<LoanResponse> getLoan(
            @PathVariable @PositiveOrZero @NotNull Long Id
    ){
        return ResponseEntity.ok().body(service.getLoan(Id));
    }

    @GetMapping("loans/actives")
    public ResponseEntity<List<LoanResponse>> getActivesLoans(){
        return ResponseEntity.ok().body(service.getActivesLoans());
    }

    @DeleteMapping("loans/{Id}")
    public ResponseEntity<Void> finishLoan(
            @PathVariable @PositiveOrZero @NotNull Long Id){
        service.finishLoan(Id);
        return ResponseEntity.ok().build();
    }

    LoanController(LoanService service){
        this.service = service;
    }
}
