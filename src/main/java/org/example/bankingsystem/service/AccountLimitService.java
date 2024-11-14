package org.example.bankingsystem.service;

import org.example.bankingsystem.model.AccountLimit;

import java.util.List;

public interface AccountLimitService {

    AccountLimit save(AccountLimit accountLimit);

    AccountLimit findById(Long id);

    List<AccountLimit> findAll();

    AccountLimit update(AccountLimit accountLimit);

    void delete(AccountLimit accountLimit);
}