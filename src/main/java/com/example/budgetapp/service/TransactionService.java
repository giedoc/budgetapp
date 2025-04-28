package com.example.budgetapp.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.budgetapp.dto.TransactionRequest;
import com.example.budgetapp.model.Transaction;

public interface TransactionService {

    Transaction addTransaction(TransactionRequest request);

    List <Transaction> getUserTransaction();

    Transaction updateTransaction(Long id, TransactionRequest request);

    void deleteTransaction(Long id);

    BigDecimal getTotalIncome();

    BigDecimal getTotalExpense();
    
} 