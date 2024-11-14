package org.example.bankingsystem.service;

import org.example.bankingsystem.model.LoanPayment;

import java.util.List;

public interface LoanPaymentService {

    LoanPayment save(LoanPayment loanPayment);

    LoanPayment findById(Long id);

    List<LoanPayment> findAll();

    LoanPayment update(LoanPayment loanPayment);

    void delete(LoanPayment loanPayment);
}