package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO representing client details")
public class ClientPayloadDTO {
    @NotBlank(message = "First Name is required")
    @Schema(description = "First Name of the client", example = "Elon")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Schema(description = "Last Name of the client", example = "Musk")
    private String lastName;

    @NotBlank(message = "Birth date is required")
    @Schema(description = "Birth date of the client", example = "1971-01-01")
    private LocalDate birthdate;

    @NotBlank(message = "Phone is required")
    @Schema(description = "Phone number of the client", example = "37367465645")
    private String phone;

    @Schema(description = "Address of the client", example = "Str. Stefan cel mare 1")
    private String address;
}
