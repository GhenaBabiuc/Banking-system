package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Loan;

import java.util.List;

public interface LoanService {

    Loan save(Loan loan);

    Loan findById(Long id);

    List<Loan> findAll();

    Loan update(Loan loan);

    void delete(Loan loan);
}