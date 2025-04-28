package com.example.budgetapp.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.budgetapp.dto.TransactionRequest;
import com.example.budgetapp.model.Transaction;
import com.example.budgetapp.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.addTransaction(request));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getUserTransaction() {
        return ResponseEntity.ok(transactionService.getUserTransaction());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/income")
    public ResponseEntity<BigDecimal> getTotalIncome() {
        return ResponseEntity.ok(transactionService.getTotalIncome());
    }

    @GetMapping("/expense")
    public ResponseEntity<BigDecimal> getTotalExpense() {
        return ResponseEntity.ok(transactionService.getTotalExpense());
    }

}
