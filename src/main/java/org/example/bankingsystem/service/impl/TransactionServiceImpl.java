package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.Currency;
import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.model.TransactionType;
import org.example.bankingsystem.model.dto.TransactionPayloadDTO;
import org.example.bankingsystem.model.dto.TransactionResponseDTO;
import org.example.bankingsystem.repository.TransactionRepository;
import org.example.bankingsystem.service.AccountService;
import org.example.bankingsystem.service.ExchangeRateService;
import org.example.bankingsystem.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Resource
    private TransactionRepository transactionRepository;

    @Resource
    private AccountService accountService;

    @Resource
    private ExchangeRateService exchangeRateService;

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionPayloadDTO transactionPayloadDTO) {
        try {
            Account destinationAccount = accountService.findById(transactionPayloadDTO.getDestinationAccountId());
            TransactionType transactionType = transactionPayloadDTO.getTransactionType();
            Currency transactionCurrency = transactionPayloadDTO.getCurrency();
            Currency accountCurrency = destinationAccount.getCurrency();

            if (transactionType == TransactionType.DEPOSIT) {
                BigDecimal amount = transactionPayloadDTO.getAmount();

                if (transactionCurrency != accountCurrency) {
                    BigDecimal exchangeRate = exchangeRateService.getExchangeRate(transactionCurrency, accountCurrency);
                    amount = amount.multiply(exchangeRate);
                }

                destinationAccount.deposit(amount);
                accountService.update(destinationAccount);

            } else if (transactionType == TransactionType.WITHDRAW) {
                BigDecimal amount = transactionPayloadDTO.getAmount();

                if (transactionCurrency != accountCurrency) {
                    BigDecimal exchangeRate = exchangeRateService.getExchangeRate(transactionCurrency, accountCurrency);
                    amount = amount.multiply(exchangeRate);
                }

                if (destinationAccount.getBalance().compareTo(amount) < 0) {
                    throw new IllegalArgumentException("Insufficient balance for withdrawal.");
                }

                destinationAccount.withdraw(amount);
                accountService.update(destinationAccount);

            } else if (transactionType == TransactionType.TRANSFER) {
                Account sourceAccount = accountService.findById(transactionPayloadDTO.getSourceAccountId());
                BigDecimal amount = transactionPayloadDTO.getAmount();

                if (transactionCurrency != sourceAccount.getCurrency()) {
                    BigDecimal exchangeRate = exchangeRateService.getExchangeRate(transactionCurrency, sourceAccount.getCurrency());
                    amount = amount.multiply(exchangeRate);
                }

                if (sourceAccount.getBalance().compareTo(amount) < 0) {
                    throw new IllegalArgumentException("Insufficient balance in source account.");
                }

                sourceAccount.withdraw(amount);
                amount = transactionPayloadDTO.getAmount();

                if (transactionCurrency != destinationAccount.getCurrency()) {
                    BigDecimal exchangeRate = exchangeRateService.getExchangeRate(transactionCurrency, destinationAccount.getCurrency());
                    amount = amount.multiply(exchangeRate);
                }

                destinationAccount.deposit(amount);
                accountService.update(sourceAccount);
                accountService.update(destinationAccount);
            }

            Transaction transaction = convertFromTransactionPayloadDTO(transactionPayloadDTO);
            Transaction savedTransaction = save(transaction);

            return convertToTransactionResponseDTO(savedTransaction);
        } catch (Exception e) {
            throw new RuntimeException("Error creating transaction: " + e.getMessage(), e);
        }
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        return convertToTransactionResponseDTO(findById(id));
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream()
                .map(this::convertToTransactionResponseDTO)
                .toList();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsForAccount(Long accountId) {
        List<Transaction> transactions = findBySourceAccountIdOrDestinationAccountId(accountId, accountId);

        return transactions.stream()
                .map(this::convertToTransactionResponseDTO)
                .toList();
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findBySourceAccountIdOrDestinationAccountId(Long sourceAccountId, Long destinationAccountId) {
        return transactionRepository.findBySourceAccountIdOrDestinationAccountId(sourceAccountId, destinationAccountId);
    }

    public Transaction convertFromTransactionPayloadDTO(TransactionPayloadDTO dto) {
        Account sourceAccount = dto.getSourceAccountId() != null
                ? accountService.findById(dto.getSourceAccountId())
                : null;

        Account destinationAccount = accountService.findById(dto.getDestinationAccountId());

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

    public TransactionResponseDTO convertToTransactionResponseDTO(Transaction transaction) {
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
