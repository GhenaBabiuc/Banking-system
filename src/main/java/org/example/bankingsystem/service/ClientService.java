package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Client;
import org.example.bankingsystem.model.dto.ClientPayloadDTO;
import org.example.bankingsystem.model.dto.ClientResponseDTO;

import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);

    Optional<Client> getClientById(Long id);

    ClientResponseDTO convertToClientResponseDto(Client client);

    Client convertFromClientPayloadDto(ClientPayloadDTO clientPayloadDTO);
}
