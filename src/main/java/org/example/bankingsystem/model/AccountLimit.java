package org.example.bankingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_limits")
public class AccountLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;

    @Column(name = "daily_withdrawal_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal dailyWithdrawalLimit;

    @Column(name = "daily_transfer_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal dailyTransferLimit;

    @Column(name = "monthly_transfer_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyTransferLimit;

}