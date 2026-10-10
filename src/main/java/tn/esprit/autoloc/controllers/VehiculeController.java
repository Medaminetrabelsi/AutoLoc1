package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicules")
public class VehiculeController {

    private final IVehiculeService vs;

    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vs.retrieveAllVehicules();
    }

    @PostMapping
    public Vehicule addVehicule(@RequestBody Vehicule v) {
        return vs.addVehicule(v);
    }

    @PutMapping
    public Vehicule updateVehicule(@RequestBody Vehicule v) {
        return vs.updateVehicule(v);
    }

    @GetMapping("/{id}")
    public Vehicule getVehicule(@PathVariable Long id) {
        return vs.retrieveVehicule(id);
    }

    @DeleteMapping("/{id}")
    public void removeVehicule(@PathVariable Long id) {
        vs.removeVehicule(id);
    }
}
