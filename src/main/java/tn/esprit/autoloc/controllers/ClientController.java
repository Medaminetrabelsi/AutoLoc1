package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    IClientService cs;

    @GetMapping
    public List<Client> getAllClients() {
        return cs.retrieveAllClients();
    }

    @PostMapping
    public Client addClient(@RequestBody Client c) {
        return cs.addClient(c);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client c) {
        return cs.updateClient(c);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return cs.retrieveClient(id);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable Long id) {
        cs.removeClient(id);
    }
}