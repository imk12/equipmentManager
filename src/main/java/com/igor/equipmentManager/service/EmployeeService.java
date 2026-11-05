package com.igor.equipmentManager.service;

import com.igor.equipmentManager.dto.employee.EmployeeCreation;
import com.igor.equipmentManager.dto.employee.EmployeeResponse;
import com.igor.equipmentManager.dto.employee.EmployeeUpdateEmail;
import com.igor.equipmentManager.dto.employee.EmployeeUpdateSector;
import com.igor.equipmentManager.entity.Employee;
import com.igor.equipmentManager.entity.Loan;
import com.igor.equipmentManager.exception.AlreadyHasEmailException;
import com.igor.equipmentManager.exception.EmployeeNotFoundException;
import com.igor.equipmentManager.repository.EmployeeRepository;
import com.igor.equipmentManager.repository.LoanRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private EmployeeRepository repository;
    private LoanRepository loanRepository;

    @Transactional
    //Registra um funcionário
    public EmployeeResponse registerAnEmployee(EmployeeCreation employeeCreation){
        if (repository.existsByEmail(employeeCreation.email())){
            throw new AlreadyHasEmailException(employeeCreation.email());
        }

        Employee saved = repository.save(toEmployee(employeeCreation));
        return toResponse(saved);
    }

    //Retorna todos os funcionários
    public List<EmployeeResponse> getEmployees(){
        List<EmployeeResponse> responses = repository.findAll().stream()
                .map(e -> new EmployeeResponse(e.getId(), e.getName(), e.getSector()))
                .toList();

        return responses;
    }

    //Retorna o funcionário específico
    public EmployeeResponse getEmployee(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Id",id));

        return toResponse(employee);
    }

    @Transactional
    //Atualiza o setor
    public void updateSector(Long id,EmployeeUpdateSector employeeUpdateSector){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Id",id));

        employee.setSector(employeeUpdateSector.sector());
    }

    @Transactional
    //Atualiza o email
    public void updateEmail(Long id,EmployeeUpdateEmail employeeUpdateEmail){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Id",id));

        employee.setEmail(employeeUpdateEmail.email());
    }

    @Transactional
    //Remove o funcionário
    public void removeEmployee(Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Id",id));

        if(!(loanRepository.existsByEmployeeId(id))){
            repository.delete(employee);
        }
        System.err.println("Não pode deletar um funcionário que está com um empréstimo");
    }

    private Employee toEmployee(EmployeeCreation employeeCreation){
        return new Employee(employeeCreation.sector(), employeeCreation.email(), employeeCreation.name());
    }

    private EmployeeResponse toResponse(Employee employee){
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getSector());
    }

    EmployeeService(
            EmployeeRepository repository,
            LoanRepository loanRepository){
        this.repository = repository;
        this.loanRepository = loanRepository;
    }
}
