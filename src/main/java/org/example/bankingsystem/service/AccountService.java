package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Account;
import org.example.bankingsystem.model.dto.AccountPayloadDTO;
import org.example.bankingsystem.model.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    AccountResponseDTO createAccount(AccountPayloadDTO accountPayloadDTO);

    AccountResponseDTO getAccountById(Long id);

    List<AccountResponseDTO> getAllAccounts();

    AccountResponseDTO updateAccount(Long id, AccountPayloadDTO accountPayloadDTO);

    void deleteAccountById(Long id);

    AccountResponseDTO convertToResponseDto(Account account);

    Account convertFromPayloadDto(AccountPayloadDTO accountPayloadDto);
}
