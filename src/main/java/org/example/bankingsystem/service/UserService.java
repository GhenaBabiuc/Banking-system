package org.example.bankingsystem.service;

import org.example.bankingsystem.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    User update(User user);

    void delete(User user);
}