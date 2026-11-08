package com.igor.equipmentManager.service;

import com.igor.equipmentManager.dto.loan.LoanCreation;
import com.igor.equipmentManager.dto.loan.LoanResponse;
import com.igor.equipmentManager.entity.Loan;
import com.igor.equipmentManager.exception.LoanNotFoundException;
import com.igor.equipmentManager.repository.EmployeeRepository;
import com.igor.equipmentManager.repository.EquipmentRepository;
import com.igor.equipmentManager.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoanService {

    private EquipmentRepository equipmentRepository;
    private LoanRepository repository;
    private EmployeeRepository employeeRepository;

    @Transactional
    //Cria um loan
    public LoanResponse createLoan(LoanCreation loanCreation){
        Loan saved = repository.save(toLoan(loanCreation));
        return toResponse(saved);
    }
    //Retorna todos os loans
    public List<LoanResponse> getLoans(){
        List<LoanResponse> responseList = repository.findAll().stream()
                .map(e -> toResponse(e))
                .toList();

        return responseList;
    }

    //Retorna o loan
    public LoanResponse getLoan(Long id){
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Id",id));

        return toResponse(loan);
    }

    public List<LoanResponse> getActivesLoans(){
        List<LoanResponse> responseList = repository.findAllByReturnDateIsNull().stream()
                .map(e -> toResponse(e))
                .toList();

        return responseList;
    }

    @Transactional
    //Conclui o loan
    public void finishLoan(Long id){
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Id",id));

        loan.setReturnDate(LocalDate.now());

        Loan saved = repository.save(loan);
    }

    public Loan toLoan(LoanCreation loanCreation){
        return new Loan(loanCreation.employee(),loanCreation.equipment());
    }

    public LoanResponse toResponse(Loan loan){
        return new LoanResponse(loan.getId(),loan.getWithDrawlDate());
    }

    LoanService(EquipmentRepository equipmentRepository,
                LoanRepository loanRepository,
                EmployeeRepository employeeRepository){
        this.repository = loanRepository;
        this.employeeRepository = employeeRepository;
        this.equipmentRepository = equipmentRepository;
    }
}
