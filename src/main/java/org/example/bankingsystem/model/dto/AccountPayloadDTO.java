package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing account details")
public class AccountPayloadDTO {

    @NotBlank(message = "Account number is required")
    @Schema(description = "Account number", example = "12345678901234567890")
    private String number;

    @NotBlank(message = "Client id is required")
    @Schema(description = "Client's unique identifier who owns the account", example = "2")
    private Long clientId;

    @NotBlank(message = "Account type id is required")
    @Schema(description = "Account type identifier", example = "1")
    private Long accountTypeId;

    @NotBlank(message = "Balance is required")
    @Schema(description = "Current balance of the account", example = "5000.00")
    private BigDecimal balance;

    @NotBlank(message = "Currency is required")
    @Schema(description = "Currency of the account", example = "USD")
    private String currency;

    @NotBlank(message = "Created At is required")
    @Schema(description = "Date the account was created", example = "2024-01-01")
    private LocalDate createdAt;

    @NotBlank(message = "Status is required")
    @Schema(description = "Status of the account", example = "ACTIVE")
    private String status;
}