package org.example.bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @PostMapping()
    public ResponseEntity<?> createLoan() {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllLoans() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
