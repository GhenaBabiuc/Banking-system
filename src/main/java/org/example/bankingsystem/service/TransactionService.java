package org.example.bankingsystem.service;

import jakarta.transaction.Transactional;
import org.example.bankingsystem.model.dto.TransactionPayloadDTO;
import org.example.bankingsystem.model.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {

    @Transactional
    TransactionResponseDTO createTransaction(TransactionPayloadDTO transactionPayloadDTO);

    TransactionResponseDTO getTransactionById(Long id);

    List<TransactionResponseDTO> getAllTransactions();

    List<TransactionResponseDTO> getTransactionsForAccount(Long accountId);
}
