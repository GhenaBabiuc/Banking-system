package org.example.bankingsystem.controller;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        Account updatedAccount = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        Account updatedAccount = accountService.withdraw(accountId, amount);
        return ResponseEntity.ok(updatedAccount);
    }
}
