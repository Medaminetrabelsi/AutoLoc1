package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    IClientService clientService;
    // http://localhost:8089/gestionLocation/client
    @GetMapping()
    public List<Client> getClients() {

        return clientService.retrieveAllClients();
    }
    // http://localhost:8089/gestionclients/client/8
    @GetMapping("/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.retrieveClient(clientId);
    }
    // http://localhost:8089/gestionclients/client
    @PostMapping()
    public Client addClient(@RequestBody Client c) {

        return clientService.addClient(c);
    }
    // http://localhost:8089/gestionclients/client/{client-id}
    // @DeleteMapping("/{clientId}")
    //public void removeClient(@PathVariable Long clientId) {
    //    clientService.removeClient(clientId);
    // }
    @DeleteMapping
    public void removeClient(@RequestParam Long clientId) {

        clientService.removeClient(clientId);
    }

    // http://localhost:8089/gestionclients/client
    @PutMapping("")
    public Client modifyClient(@RequestBody Client c) {
        return clientService.updateClient(c);
    }
}

