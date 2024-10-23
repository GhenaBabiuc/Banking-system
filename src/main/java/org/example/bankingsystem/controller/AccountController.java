package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.dto.AccountPayloadDTO;
import org.example.bankingsystem.model.dto.AccountResponseDTO;
import org.example.bankingsystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Operation(summary = "Create account",
            description = "Creating a new account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created"),
            @ApiResponse(responseCode = "404", description = "Account not created", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountPayloadDTO accountPayloadDto) {
        Account savedAccount = accountService.createAccount(accountService.convertFromPayloadDto(accountPayloadDto));

        return ResponseEntity.ok(accountService.convertToResponseDto(savedAccount));
    }

    @GetMapping()
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok().build();
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

}
