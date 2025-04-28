package com.example.budgetapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.budgetapp.dto.TransactionRequest;
import com.example.budgetapp.model.Transaction;
import com.example.budgetapp.model.User;
import com.example.budgetapp.repository.TransactionRepository;
import com.example.budgetapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public Transaction addTransaction(TransactionRequest request) {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                     .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .description(request.getDescription())
                .date(request.getDate())
                .user(user)
                .build();

        return transactionRepository.save(transaction);
    }
    
    @Override
    public List<Transaction>getUserTransaction()  {
        String username =  getCurrentUsername();
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }
    
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails) {
            return((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public Transaction updateTransaction(Long id, TransactionRequest request)  {
        Transaction transaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Transaction not found!"));
        
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Transaction not found!"));

        transactionRepository.delete(transaction);
    }

    @Override
    public BigDecimal getTotalIncome() {
        String username = getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return transactionRepository.findByUser(user).stream()
                .filter(transaction ->"INCOME".equals(transaction.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getTotalExpense() {
        String username = getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not Found!"));

        return transactionRepository.findByUser(user).stream()
                .filter(transaction -> "EXPENSE".equals(transaction.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
