package com.igor.equipmentManager.repository;

import com.igor.equipmentManager.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
