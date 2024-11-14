package org.example.bankingsystem.service;

import org.example.bankingsystem.model.AccountType;

import java.util.List;

public interface AccountTypeService {

    AccountType save(AccountType accountType);

    AccountType findById(Long id);

    List<AccountType> findAll();

    AccountType update(AccountType accountType);

    void delete(AccountType accountType);
}