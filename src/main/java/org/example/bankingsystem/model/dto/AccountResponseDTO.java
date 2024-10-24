package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO representing account details")
public class AccountResponseDTO {
    @Schema(description = "Unique identifier of the account", example = "1")
    private Long id;

    @Schema(description = "Account number", example = "12345678901234567890")
    private String number;

    @Schema(description = "Client's unique identifier who owns the account", example = "2")
    private Long clientId;

    @Schema(description = "Account type identifier", example = "1")
    private Long accountTypeId;

    @Schema(description = "Current balance of the account", example = "5000.00")
    private BigDecimal balance;

    @Schema(description = "Currency of the account", example = "USD")
    private String currency;

    @Schema(description = "Date the account was created", example = "2024-01-01")
    private LocalDate createdAt;

    @Schema(description = "Status of the account", example = "ACTIVE")
    private String status;
}
