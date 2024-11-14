package org.example.bankingsystem.service;

import org.example.bankingsystem.model.Card;

import java.util.List;

public interface CardService {

    Card save(Card card);

    Card findById(Long id);

    List<Card> findAll();

    Card update(Card card);

    void delete(Card card);
}