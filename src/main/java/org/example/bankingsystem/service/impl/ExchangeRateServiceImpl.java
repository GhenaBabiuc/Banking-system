package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Currency;
import org.example.bankingsystem.model.ExchangeRate;
import org.example.bankingsystem.repository.ExchangeRateRepository;
import org.example.bankingsystem.service.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Resource
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public BigDecimal getExchangeRate(Currency baseCurrency, Currency targetCurrency) {
        if (baseCurrency.equals(targetCurrency)) {
            return BigDecimal.ONE;
        }

        Optional<ExchangeRate> rate = exchangeRateRepository.findTopByBaseCurrencyAndTargetCurrencyOrderByDateDesc(baseCurrency, targetCurrency);

        return rate.map(ExchangeRate::getRate)
                .orElseThrow(() -> new IllegalArgumentException("Exchange rate not found for currencies: " + baseCurrency + " to " + targetCurrency));
    }
}
