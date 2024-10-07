package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Client;
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

}
