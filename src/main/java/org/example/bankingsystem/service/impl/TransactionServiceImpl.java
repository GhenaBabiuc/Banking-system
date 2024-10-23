package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.model.dto.TransactionPayloadDTO;
import org.example.bankingsystem.model.dto.TransactionResponseDTO;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.repository.TransactionRepository;
import org.example.bankingsystem.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Resource
    private TransactionRepository transactionRepository;

    @Resource
    private AccountRepository accountRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionRepository.findBySourceAccountIdOrDestinationAccountId(accountId, accountId);
    }

    @Override
    public Transaction convertFromPayloadDTO(TransactionPayloadDTO dto) {
        Account sourceAccount = dto.getSourceAccountId() != null
                ? accountRepository.findById(dto.getSourceAccountId()).orElseThrow(() -> new IllegalArgumentException("Source account not found"))
                : null;

        Account destinationAccount = accountRepository.findById(dto.getDestinationAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        return Transaction.builder()
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .transactionType(dto.getTransactionType())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .description(dto.getDescription())
                .createdAt(Instant.now())
                .build();
    }

    @Override
    public TransactionResponseDTO convertToResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .sourceAccountNumber(transaction.getSourceAccount() != null ? transaction.getSourceAccount().getNumber() : null)
                .destinationAccountNumber(transaction.getDestinationAccount().getNumber())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .createdAt(transaction.getCreatedAt())
                .description(transaction.getDescription())
                .build();
    }
}
