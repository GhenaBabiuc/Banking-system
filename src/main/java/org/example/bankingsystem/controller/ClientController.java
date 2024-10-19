package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Client;
import org.example.bankingsystem.model.dto.ClientPayloadDTO;
import org.example.bankingsystem.model.dto.ClientResponseDTO;
import org.example.bankingsystem.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Resource
    private ClientService clientService;

    @Operation(summary = "Create client",
            description = "Creating a new client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created"),
            @ApiResponse(responseCode = "404", description = "Client not created", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientPayloadDTO clientPayloadDTO) {
        Client client = clientService.convertFromClientPayloadDto(clientPayloadDTO);
        Client savedClient = clientService.createClient(client);

        return ResponseEntity.ok(clientService.convertToClientResponseDto(savedClient));
    }

    @Operation(summary = "Get clients",
            description = "Fetches the clients details, requires user to be authenticated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found"),
            @ApiResponse(responseCode = "404", description = "Clients not found", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientResponseDTO> clientResponseDTOS = clients.stream().map(client -> clientService.convertToClientResponseDto(client)).toList();

        return ResponseEntity.ok(clientResponseDTOS);
    }

    @Operation(summary = "Get client by ID",
            description = "Fetches the client details by client ID, requires user to be authenticated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> ResponseEntity.ok(clientService.convertToClientResponseDto(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
