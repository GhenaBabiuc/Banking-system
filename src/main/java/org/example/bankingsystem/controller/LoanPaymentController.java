package org.example.bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan-payments")
public class LoanPaymentController {

    @PostMapping()
    public ResponseEntity<?> createLoanPayment() {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllLoanPayments() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}