package com.example.budgetapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class TransactionRequest {
    private BigDecimal amount;
    private String type; // Income or Expense
    private String description;
    private LocalDate date;
}
