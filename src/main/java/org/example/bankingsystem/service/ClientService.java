package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Client;
import org.example.bankingsystem.model.dto.ClientPayloadDTO;
import org.example.bankingsystem.model.dto.ClientResponseDTO;

import java.util.List;

public interface ClientService {

    ClientResponseDTO createClient(ClientPayloadDTO clientPayloadDTO);

    ClientResponseDTO getClientById(Long id);

    List<ClientResponseDTO> getAllClients();

    ClientResponseDTO updateClient(Long id, ClientPayloadDTO clientPayloadDTO);

    void deleteClientById(Long id);

    ClientResponseDTO convertToClientResponseDto(Client client);

    Client convertFromClientPayloadDto(ClientPayloadDTO clientPayloadDTO);
}
