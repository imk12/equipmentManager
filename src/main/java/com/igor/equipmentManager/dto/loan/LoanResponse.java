package com.igor.equipmentManager.dto.loan;

import java.time.LocalDate;

public record LoanResponse(Long id, LocalDate withDrawlDate) {
}
