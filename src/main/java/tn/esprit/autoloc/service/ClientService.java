package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    ClientRepository repo;

    @Override
    public List<Client> retrieveAllClients() {
        return repo.findAll();
    }

    @Override
    public Client addClient(Client c) {
        return repo.save(c);
    }

    @Override
    public Client updateClient(Client c) {
        return repo.save(c);
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return repo.findById(idClient).orElse(null);
    }

    @Override
    public void removeClient(Long idClient) {
        repo.deleteById(idClient);
    }

    @Override
    public List<Client> addClients(List<Client> clients) {
        return repo.saveAll(clients);
    }
}