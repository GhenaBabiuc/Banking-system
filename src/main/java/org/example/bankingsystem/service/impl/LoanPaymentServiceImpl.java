package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.example.bankingsystem.model.LoanPayment;
import org.example.bankingsystem.repository.LoanPaymentRepository;
import org.example.bankingsystem.service.LoanPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanPaymentServiceImpl implements LoanPaymentService {

    @Resource
    private LoanPaymentRepository loanPaymentRepository;

    @Override
    public LoanPayment save(LoanPayment loanPayment) {
        return loanPaymentRepository.save(loanPayment);
    }

    @Override
    public LoanPayment findById(Long id) {
        return loanPaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LoanPayment not found with ID: " + id));
    }

    @Override
    public List<LoanPayment> findAll() {
        return loanPaymentRepository.findAll();
    }

    @Override
    public LoanPayment update(LoanPayment loanPayment) {
        return loanPaymentRepository.save(loanPayment);
    }

    @Override
    public void delete(LoanPayment loanPayment) {
        loanPaymentRepository.delete(loanPayment);
    }
}