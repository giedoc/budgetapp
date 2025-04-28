package com.example.budgetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.budgetapp.model.Transaction;
import com.example.budgetapp.model.User;

import java.util.List;


public interface TransactionRepository extends JpaRepository <Transaction, Long > {
    List<Transaction> findByUser(User user);
}
