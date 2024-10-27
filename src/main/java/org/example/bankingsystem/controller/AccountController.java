package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.example.bankingsystem.model.dto.AccountPayloadDTO;
import org.example.bankingsystem.model.dto.AccountResponseDTO;
import org.example.bankingsystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Operation(summary = "Create account", description = "Creating a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account created"),
            @ApiResponse(responseCode = "404", description = "Account not created", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountPayloadDTO accountPayloadDto) {
        AccountResponseDTO accountResponseDTO = accountService.createAccount(accountPayloadDto);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @Operation(summary = "Get accounts", description = "Fetches the accounts details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts found"),
            @ApiResponse(responseCode = "404", description = "Accounts not found", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Get account by ID", description = "Fetches the account details by account ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        AccountResponseDTO accountResponseDTO = accountService.getAccountById(id);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @Operation(summary = "Update account", description = "Updates a account's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id, @RequestBody AccountPayloadDTO accountPayloadDto) {
        AccountResponseDTO accountResponseDTO = accountService.updateAccount(id, accountPayloadDto);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @Operation(summary = "Delete account", description = "Deletes a account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok().build();
    }

}
