package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.example.bankingsystem.model.Currency;
import org.example.bankingsystem.model.TransactionType;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Payload for creating a transaction")
public class TransactionPayloadDTO {

    @Schema(description = "ID of the source account for the transaction", example = "1", nullable = true)
    private Long sourceAccountId;

    @Schema(description = "ID of the destination account for the transaction", example = "2", required = true)
    private Long destinationAccountId;

    @Schema(description = "Type of the transaction", example = "TRANSFER", required = true)
    private TransactionType transactionType;

    @Schema(description = "Transaction amount", example = "100.00", required = true)
    private BigDecimal amount;

    @Schema(description = "Currency of the transaction", example = "USD", required = true)
    private Currency currency;

    @Schema(description = "Description of the transaction", example = "Payment for services", nullable = true)
    private String description;

}
