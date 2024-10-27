package org.example.bankingsystem.repository;

import org.example.bankingsystem.model.Currency;
import org.example.bankingsystem.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findTopByBaseCurrencyAndTargetCurrencyOrderByDateDesc(Currency baseCurrency, Currency targetCurrency);
}
