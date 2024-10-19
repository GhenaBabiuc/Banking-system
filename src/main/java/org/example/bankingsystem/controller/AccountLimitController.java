package org.example.bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-limits")
public class AccountLimitController {

    @PostMapping()
    public ResponseEntity<?> createAccountLimit() {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllAccountLimits() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountLimitById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccountLimit(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
