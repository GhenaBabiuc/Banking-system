package org.example.bankingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
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

    @Operation(summary = "Create client", description = "Creating a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created"),
            @ApiResponse(responseCode = "404", description = "Client not created", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientPayloadDTO clientPayloadDTO) {
        ClientResponseDTO responseDTO = clientService.createClient(clientPayloadDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Get clients", description = "Fetches the clients details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found"),
            @ApiResponse(responseCode = "404", description = "Clients not found", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<ClientResponseDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Get client by ID", description = "Fetches the client details by client ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id) {
        ClientResponseDTO clientResponse = clientService.getClientById(id);
        return ResponseEntity.ok(clientResponse);
    }

    @Operation(summary = "Update client", description = "Updates a client's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientPayloadDTO clientPayloadDTO) {
        ClientResponseDTO updatedClient = clientService.updateClient(id, clientPayloadDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @Operation(summary = "Delete client", description = "Deletes a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }
}