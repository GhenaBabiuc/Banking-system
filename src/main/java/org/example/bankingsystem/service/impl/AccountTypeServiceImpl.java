package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.example.bankingsystem.model.AccountType;
import org.example.bankingsystem.repository.AccountTypeRepository;
import org.example.bankingsystem.service.AccountTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Resource
    private AccountTypeRepository accountTypeRepository;

    @Override
    public AccountType save(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public AccountType findById(Long id) {
        return accountTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountType not found with ID: " + id));
    }

    @Override
    public List<AccountType> findAll() {
        return accountTypeRepository.findAll();
    }

    @Override
    public AccountType update(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public void delete(AccountType accountType) {
        accountTypeRepository.delete(accountType);
    }
}