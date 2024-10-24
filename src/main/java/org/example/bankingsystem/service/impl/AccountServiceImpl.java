package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.example.bankingsystem.model.*;
import org.example.bankingsystem.model.dto.AccountPayloadDTO;
import org.example.bankingsystem.model.dto.AccountResponseDTO;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public AccountResponseDTO createAccount(AccountPayloadDTO accountPayloadDTO) {
        try {
            Account account = convertFromPayloadDto(accountPayloadDTO);
            Account savedAccount = save(account);

            return convertToResponseDto(savedAccount);
        } catch (Exception e) {
            throw new RuntimeException("Error creating client: " + e.getMessage(), e);
        }
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        return convertToResponseDto(findById(id));
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Override
    public AccountResponseDTO updateAccount(Long id, AccountPayloadDTO accountPayloadDTO) {
        Account existingAccount = findById(id);

        try {
            updateFields(existingAccount, accountPayloadDTO);
            Account updatedAccount = update(existingAccount);

            return convertToResponseDto(updatedAccount);
        } catch (Exception e) {
            throw new RuntimeException("Error updating account with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        Account account = findById(id);

        try {
            delete(account);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Account not found for deletion with ID: " + id, e);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting account with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + id));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account update(Account account) {
        return accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    private void updateFields(Account existingAccount, AccountPayloadDTO accountPayloadDTO) {
        if (accountPayloadDTO.getNumber() != null) {
            existingAccount.setNumber(accountPayloadDTO.getNumber());
        }
        if (accountPayloadDTO.getClientId() != null) {
            existingAccount.setClient(Client.builder().id(accountPayloadDTO.getClientId()).build());
        }
        if (accountPayloadDTO.getAccountTypeId() != null) {
            existingAccount.setAccountType(AccountType.builder().id(accountPayloadDTO.getAccountTypeId()).build());
        }
        if (accountPayloadDTO.getBalance() != null) {
            existingAccount.setBalance(accountPayloadDTO.getBalance());
        }
        if (accountPayloadDTO.getCurrency() != null) {
            existingAccount.setCurrency(Currency.valueOf(accountPayloadDTO.getCurrency()));
        }
        if (accountPayloadDTO.getCreatedAt() != null) {
            existingAccount.setCreatedAt(accountPayloadDTO.getCreatedAt());
        }
        if (accountPayloadDTO.getStatus() != null) {
            existingAccount.setStatus(AccountStatus.valueOf(accountPayloadDTO.getStatus()));
        }
    }

    @Override
    public AccountResponseDTO convertToResponseDto(Account account) {
        return AccountResponseDTO.builder()
                .id(account.getId())
                .number(account.getNumber())
                .clientId(account.getClient().getId())
                .accountTypeId(account.getAccountType().getId())
                .balance(account.getBalance())
                .currency(account.getCurrency().toString())
                .createdAt(account.getCreatedAt())
                .status(account.getStatus().toString())
                .build();
    }

    @Override
    public Account convertFromPayloadDto(AccountPayloadDTO accountPayloadDto) {
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
