package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing account details")
public class AccountPayloadDto {
    @Schema(description = "Account number", example = "12345678901234567890", required = true)
    private String number;

    @Schema(description = "Client's unique identifier who owns the account", example = "2", required = true)
    private Long clientId;

    @Schema(description = "Account type identifier", example = "1", required = true)
    private Long accountTypeId;

    @Schema(description = "Current balance of the account", example = "5000.00", required = true)
    private BigDecimal balance;

    @Schema(description = "Currency of the account", example = "USD", required = true)
    private String currency;

    @Schema(description = "Date the account was created", example = "2024-01-01", required = true)
    private LocalDate createdAt;

    @Schema(description = "Status of the account", example = "ACTIVE", required = true)
    private String status;
}
