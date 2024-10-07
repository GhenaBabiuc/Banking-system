package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Client;

import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);

    Optional<Client> getClientById(Long id);
}
