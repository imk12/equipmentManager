package com.igor.equipmentManager.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_Id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "employee_Id")
    private Employee employee;

    @Column(nullable = false)
    private LocalDate withDrawlDate;
    @Column(nullable = true)
    private LocalDate returnDate;

    //Getter and Setter

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getWithDrawlDate() {
        return withDrawlDate;
    }

    public Optional<LocalDate> getReturnDate() {
        return Optional.ofNullable(returnDate);
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    //Constructor


    protected Loan() {}

    public Loan(Employee employee, Equipment equipment) {
        this.employee = employee;
        this.equipment = equipment;
        withDrawlDate = LocalDate.now();
    }
}
