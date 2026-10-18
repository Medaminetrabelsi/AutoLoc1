package tn.esprit.autoloc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicule") // Singulier comme le prof
public class VehiculeController {

    IVehiculeService vehiculeService;

    // http://localhost:8080/vehicule
    @GetMapping()
    public List<Vehicule> getVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    // http://localhost:8080/vehicule/8
    @GetMapping("/{vehicule-id}")
    public Vehicule retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        return vehiculeService.retrieveVehicule(vehiculeId);
    }

    // http://localhost:8080/vehicule
    @PostMapping()
    public Vehicule addVehicule(@RequestBody Vehicule v) {
        return vehiculeService.addVehicule(v);
    }

    // http://localhost:8080/vehicule?vehiculeId=1
    @DeleteMapping
    public void removeVehicule(@RequestParam Long vehiculeId) {
        vehiculeService.removeVehicule(vehiculeId);
    }

    // http://localhost:8080/vehicule
    @PutMapping("")
    public Vehicule updateVehicule(@RequestBody Vehicule v) {
        return vehiculeService.updateVehicule(v);
    }
}