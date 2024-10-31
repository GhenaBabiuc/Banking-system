package org.example.bankingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_currency", nullable = false, length = 3)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Currency baseCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_currency", nullable = false, length = 3)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Currency targetCurrency;

    @Column(name = "rate", nullable = false, precision = 15, scale = 6)
    private BigDecimal rate;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}