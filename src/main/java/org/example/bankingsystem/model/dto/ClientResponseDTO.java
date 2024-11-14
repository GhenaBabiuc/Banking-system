package org.example.bankingsystem.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO representing client details")
public class ClientResponseDTO {

    @Schema(description = "Unique identifier of the client", example = "1")
    private Long id;

    @Schema(description = "First Name of the client", example = "Elon")
    private String firstName;

    @Schema(description = "Last Name of the client", example = "Musk")
    private String lastName;

    @Schema(description = "Birth date of the client", example = "1971-01-01")
    private LocalDate birthdate;

    @Schema(description = "Phone number of the client", example = "37367465645")
    private String phone;

    @Schema(description = "Address of the client", example = "Str. Stefan cel mare 1")
    private String address;
}