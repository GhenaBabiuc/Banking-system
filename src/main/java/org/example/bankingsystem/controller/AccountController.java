package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.dto.AccountPayloadDto;
import org.example.bankingsystem.model.dto.AccountResponseDTO;
import org.example.bankingsystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Operation(summary = "Get account by ID",
            description = "Fetches the account details by account ID, requires user to be authenticated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(account -> ResponseEntity.ok(accountService.convertToResponseDto(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create account",
            description = "Creating a new account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created"),
            @ApiResponse(responseCode = "404", description = "Account not created", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountPayloadDto accountPayloadDto) {
        Account savedAccount = accountService.createAccount(accountService.convertFromPayloadDto(accountPayloadDto));

        return ResponseEntity.ok(accountService.convertToResponseDto(savedAccount));
    }


    @Operation(summary = "Deposit into account",
            description = "Deposits a specified amount into the account, requires user to be authenticated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit successful"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<AccountResponseDTO> deposit(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        Account updatedAccount = accountService.deposit(accountId, amount);

        return ResponseEntity.ok(accountService.convertToResponseDto(updatedAccount));
    }

    @Operation(summary = "Withdraw from account",
            description = "Withdraws a specified amount from the account, requires user to be authenticated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Withdrawal successful"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<AccountResponseDTO> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount) {
        Account updatedAccount = accountService.withdraw(accountId, amount);

        return ResponseEntity.ok(accountService.convertToResponseDto(updatedAccount));
    }
}
