package org.example.bankingsystem.controller;

import jakarta.annotation.Resource;
import org.example.bankingsystem.model.Client;
import org.example.bankingsystem.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Resource
    private ClientService clientService;

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable Long clientId) {
        Optional<Client> client = clientService.getClientById(clientId);

        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.createClient(client);

        return ResponseEntity.ok(savedClient);
    }
}
