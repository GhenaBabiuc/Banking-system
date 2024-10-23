package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.example.bankingsystem.model.Currency;
import org.example.bankingsystem.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response DTO for a transaction")
public class TransactionResponseDTO {

    @Schema(description = "ID of the transaction", example = "1")
    private Long id;

    @Schema(description = "Number of the source account", example = "1234567890123456")
    private String sourceAccountNumber;

    @Schema(description = "Number of the destination account", example = "9876543210987654")
    private String destinationAccountNumber;

    @Schema(description = "Type of the transaction", example = "TRANSFER")
    private TransactionType transactionType;

    @Schema(description = "Transaction amount", example = "100.00")
    private BigDecimal amount;

    @Schema(description = "Currency of the transaction", example = "USD")
    private Currency currency;

    @Schema(description = "Date and time of the transaction", example = "2024-10-23T10:00:00Z")
    private Instant createdAt;

    @Schema(description = "Description of the transaction", example = "Payment for services")
    private String description;
}
