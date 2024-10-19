package org.example.bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    @PostMapping()
    public ResponseEntity<?> createCard() {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllCards() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
