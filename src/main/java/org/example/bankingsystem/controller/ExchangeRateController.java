package org.example.bankingsystem.controller;

import org.example.bankingsystem.model.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    @PostMapping()
    public ResponseEntity<?> createExchangeRate() {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllExchangeRates() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExchangeRateById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/base/{baseCurrency}/target/{targetCurrency}")
    public ResponseEntity<?> getExchangeRateByCurrency(@PathVariable Currency baseCurrency, @PathVariable Currency targetCurrency) {
        return ResponseEntity.ok().build();
    }
}