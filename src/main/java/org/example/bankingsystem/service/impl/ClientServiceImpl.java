package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.example.bankingsystem.model.Client;
import org.example.bankingsystem.model.dto.ClientPayloadDTO;
import org.example.bankingsystem.model.dto.ClientResponseDTO;
import org.example.bankingsystem.repository.ClientRepository;
import org.example.bankingsystem.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    public ClientResponseDTO createClient(ClientPayloadDTO clientPayloadDTO) {
        try {
            Client client = convertFromClientPayloadDto(clientPayloadDTO);
            Client savedClient = save(client);

            return convertToClientResponseDto(savedClient);
        } catch (Exception e) {
            throw new RuntimeException("Error creating client: " + e.getMessage(), e);
        }
    }

    @Override
    public ClientResponseDTO getClientById(Long id) {
        return convertToClientResponseDto(findById(id));
    }

    @Override
    public List<ClientResponseDTO> getAllClients() {
        List<Client> clients = findAll();

        return clients.stream()
                .map(this::convertToClientResponseDto)
                .toList();
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientPayloadDTO clientPayloadDTO) {
        Client existingClient = findById(id);

        try {
            updateFields(existingClient, clientPayloadDTO);
            Client updatedClient = update(existingClient);

            return convertToClientResponseDto(updatedClient);
        } catch (Exception e) {
            throw new RuntimeException("Error updating client with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteClientById(Long id) {
        Client client = findById(id);

        try {
            delete(client);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Client not found for deletion with ID: " + id, e);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting client with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client update(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    private void updateFields(Client existingClient, ClientPayloadDTO clientPayloadDTO) {
        if (clientPayloadDTO.getFirstName() != null) {
            existingClient.setFirstName(clientPayloadDTO.getFirstName());
        }
        if (clientPayloadDTO.getLastName() != null) {
            existingClient.setLastName(clientPayloadDTO.getLastName());
        }
        if (clientPayloadDTO.getBirthdate() != null) {
            existingClient.setBirthdate(clientPayloadDTO.getBirthdate());
        }
        if (clientPayloadDTO.getPhone() != null) {
            existingClient.setPhone(clientPayloadDTO.getPhone());
        }
        if (clientPayloadDTO.getAddress() != null) {
            existingClient.setAddress(clientPayloadDTO.getAddress());
        }
    }

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