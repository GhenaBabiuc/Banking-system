package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
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

    @Operation(summary = "Create a transaction", description = "Creates a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction created"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionPayloadDTO transactionPayloadDTO) {
        TransactionResponseDTO transactionResponseDTO = transactionService.createTransaction(transactionPayloadDTO);
        return ResponseEntity.ok(transactionResponseDTO);
    }

    @Operation(summary = "Get all transactions", description = "Retrieves all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions found"),
            @ApiResponse(responseCode = "404", description = "No transactions found", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Get transaction by ID", description = "Retrieves a transaction by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
        TransactionResponseDTO transactionResponseDTO = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transactionResponseDTO);
    }

    @Operation(summary = "Get transactions for account", description = "Retrieves all transactions for a specific account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions found"),
            @ApiResponse(responseCode = "404", description = "No transactions found for the account", content = @Content)
    })
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsForAccount(@PathVariable Long accountId) {
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsForAccount(accountId);
        return ResponseEntity.ok(transactions);
    }
}