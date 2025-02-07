package com.projectSecur.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transaction;
import com.projectSecur.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Transaction save(Transaction transaction) {
        return entityManager.merge(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Transaction.class, id));
    }

    @Override
    public List<Transaction> findAll() {
        return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        if (transaction != null) {
            entityManager.remove(transaction);
        }
    }
}
