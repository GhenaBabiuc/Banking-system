package org.example.bankingsystem.repository;

import org.example.bankingsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findBySourceAccountIdOrDestinationAccountId(Long sourceAccountId, Long destinationAccountId);
}
