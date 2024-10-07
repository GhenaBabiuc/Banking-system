package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Account;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);

    Optional<Account> getAccountById(Long id);

    @Transactional
    Account deposit(Long accountId, BigDecimal amount);

    @Transactional
    Account withdraw(Long accountId, BigDecimal amount);
}
