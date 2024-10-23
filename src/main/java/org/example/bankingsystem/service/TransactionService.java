package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.model.dto.TransactionPayloadDTO;
import org.example.bankingsystem.model.dto.TransactionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    Optional<Transaction> getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsForAccount(Long accountId);

    Transaction convertFromPayloadDTO(TransactionPayloadDTO dto);

    TransactionResponseDTO convertToResponseDTO(Transaction transaction);
}
