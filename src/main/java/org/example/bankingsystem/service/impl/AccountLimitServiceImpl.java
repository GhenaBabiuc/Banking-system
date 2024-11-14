package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.example.bankingsystem.model.AccountLimit;
import org.example.bankingsystem.repository.AccountLimitRepository;
import org.example.bankingsystem.service.AccountLimitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountLimitServiceImpl implements AccountLimitService {

    @Resource
    private AccountLimitRepository accountLimitRepository;

    @Override
    public AccountLimit save(AccountLimit accountLimit) {
        return accountLimitRepository.save(accountLimit);
    }

    @Override
    public AccountLimit findById(Long id) {
        return accountLimitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountLimit not found with ID: " + id));
    }

    @Override
    public List<AccountLimit> findAll() {
        return accountLimitRepository.findAll();
    }

    @Override
    public AccountLimit update(AccountLimit accountLimit) {
        return accountLimitRepository.save(accountLimit);
    }

    @Override
    public void delete(AccountLimit accountLimit) {
        accountLimitRepository.delete(accountLimit);
    }
}