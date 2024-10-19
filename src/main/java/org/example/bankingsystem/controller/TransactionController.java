package org.example.bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping()
    public ResponseEntity<?> createTransaction() {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getAllTransactionsForAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok().build();
    }
}
