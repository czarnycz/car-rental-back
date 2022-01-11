package com.example.projektpraktyczny.service;

import com.example.projektpraktyczny.model.Client;
import com.example.projektpraktyczny.repository1.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JpaClientService implements ClientService{
    final ClientRepository clientRepository;

    public JpaClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void add(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
