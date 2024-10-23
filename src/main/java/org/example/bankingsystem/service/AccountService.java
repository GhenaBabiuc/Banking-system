package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.dto.AccountPayloadDTO;
import org.example.bankingsystem.model.dto.AccountResponseDTO;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Long id);

    Account createAccount(Account account);

    AccountResponseDTO convertToResponseDto(Account account);

    Account convertFromPayloadDto(AccountPayloadDTO accountPayloadDto);
}
