package com.projectSecur.dao;

//import jakarta.transaction.Transaction;

import com.projectSecur.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    void deleteById(Long id);
}
