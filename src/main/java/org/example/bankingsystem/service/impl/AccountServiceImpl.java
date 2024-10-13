package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.*;
import org.example.bankingsystem.model.dto.AccountPayloadDto;
import org.example.bankingsystem.model.dto.AccountResponseDTO;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }

    @Override
    public AccountResponseDTO convertToResponseDto(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getNumber(),
                account.getClient().getId(),
                account.getAccountType().getId(),
                account.getBalance(),
                account.getCurrency().toString(),
                account.getCreatedAt(),
                account.getStatus().toString()
        );
    }

    @Override
    public Account convertFromPayloadDto(AccountPayloadDto accountPayloadDto) {
        return Account.builder()
                .number(accountPayloadDto.getNumber())
                .client(Client.builder().id(accountPayloadDto.getClientId()).build())
                .accountType(AccountType.builder().id(accountPayloadDto.getAccountTypeId()).build())
                .balance(accountPayloadDto.getBalance())
                .currency(Currency.valueOf(accountPayloadDto.getCurrency()))
                .createdAt(accountPayloadDto.getCreatedAt())
                .status(AccountStatus.valueOf(accountPayloadDto.getStatus()))
                .build();
    }
}
