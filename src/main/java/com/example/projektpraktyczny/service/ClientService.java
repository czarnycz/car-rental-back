package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Car;
import com.example.projektpraktyczny.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    void add(Client client);
    void delete(Long id);
}
