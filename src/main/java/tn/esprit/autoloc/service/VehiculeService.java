package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository repo;

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return repo.findAll();
    }

    @Override
    public Vehicule addVehicule(Vehicule v) {
        return repo.save(v);
    }

    @Override
    public Vehicule updateVehicule(Vehicule v) {
        return repo.save(v);
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        return repo.findById(idVehicule).orElse(null);
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        repo.deleteById(idVehicule);
    }

    @Override
    public List<Vehicule> addVehicules(List<Vehicule> vehicules) {
        return repo.saveAll(vehicules);
    }
}