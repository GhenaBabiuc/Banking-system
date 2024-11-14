package org.example.bankingsystem.service.impl;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.example.bankingsystem.model.Card;
import org.example.bankingsystem.repository.CardRepository;
import org.example.bankingsystem.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Resource
    private CardRepository cardRepository;

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with ID: " + id));
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card update(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void delete(Card card) {
        cardRepository.delete(card);
    }
}