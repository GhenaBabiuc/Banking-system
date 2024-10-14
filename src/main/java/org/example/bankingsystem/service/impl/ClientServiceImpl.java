package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Client;
import org.example.bankingsystem.model.dto.ClientPayloadDTO;
import org.example.bankingsystem.model.dto.ClientResponseDTO;
import org.example.bankingsystem.repository.ClientRepository;
import org.example.bankingsystem.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientResponseDTO convertToClientResponseDto(Client client) {
        return ClientResponseDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .birthdate(client.getBirthdate())
                .phone(client.getPhone())
                .address(client.getAddress())
                .build();
    }

    @Override
    public Client convertFromClientPayloadDto(ClientPayloadDTO clientPayloadDTO) {
        return Client.builder()
                .firstName(clientPayloadDTO.getFirstName())
                .lastName(clientPayloadDTO.getLastName())
                .birthdate(clientPayloadDTO.getBirthdate())
                .phone(clientPayloadDTO.getPhone())
                .address(clientPayloadDTO.getAddress())
                .build();
    }

}
