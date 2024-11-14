package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Currency;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal getExchangeRate(Currency baseCurrency, Currency targetCurrency);
}