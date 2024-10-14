package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.dto.AccountPayloadDTO;
import org.example.bankingsystem.model.dto.AccountResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Long id);

    Account createAccount(Account account);

    @Transactional
    Account deposit(Long accountId, BigDecimal amount);

    @Transactional
    Account withdraw(Long accountId, BigDecimal amount);

    AccountResponseDTO convertToResponseDto(Account account);

    Account convertFromPayloadDto(AccountPayloadDTO accountPayloadDto);
}
