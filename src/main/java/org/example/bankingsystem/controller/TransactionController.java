package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Transaction;
import org.example.bankingsystem.model.dto.TransactionPayloadDTO;
import org.example.bankingsystem.model.dto.TransactionResponseDTO;
import org.example.bankingsystem.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @Operation(summary = "Create a transaction", description = "Creates a new transaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction created"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionPayloadDTO transactionPayloadDTO) {
        Transaction transaction = transactionService.convertFromPayloadDTO(transactionPayloadDTO);
        Transaction savedTransaction = transactionService.createTransaction(transaction);

        return ResponseEntity.ok(transactionService.convertToResponseDTO(savedTransaction));
    }

    @Operation(summary = "Get all transactions", description = "Retrieves all transactions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions found"),
            @ApiResponse(responseCode = "404", description = "No transactions found", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                .map(transactionService::convertToResponseDTO)
                .toList();

        return ResponseEntity.ok(transactionDTOs);
    }

    @Operation(summary = "Get transaction by ID", description = "Retrieves a transaction by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(transaction -> ResponseEntity.ok(transactionService.convertToResponseDTO(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get transactions for account", description = "Retrieves all transactions for a specific account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions found"),
            @ApiResponse(responseCode = "404", description = "No transactions found for the account", content = @Content)
    })
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsForAccount(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionsForAccount(accountId);
        List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                .map(transactionService::convertToResponseDTO)
                .toList();

        return ResponseEntity.ok(transactionDTOs);
    }
}