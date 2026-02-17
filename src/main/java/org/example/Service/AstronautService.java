package org.example.Service;

import org.example.Model.Astronaut;
import org.example.Model.AstronautStatus;
import org.example.Repository.IRepository;

import java.util.List;

public class AstronautService {

    private final IRepository<Astronaut, Integer> repo;

    public AstronautService(IRepository<Astronaut, Integer> repo) {
        this.repo = repo;
    }

    public List<Astronaut> getAll() {
        return repo.findAll();
    }


    public void displayAllAstronauts() {
        repo.findAll().forEach(astronaut -> {
            System.out.printf("[#%d] %s | %s | %s | exp=%d%n",
                    astronaut.getId(),
                    astronaut.getName(),
                    astronaut.getSpacecraft(),
                    astronaut.getStatus(),
                    astronaut.getExperienceLevel());
        });
    }



    public void displayActiveAstronautsBySpacecraft(String spacecraft) {
        repo.findAll().stream()
                .filter(astronaut -> astronaut.getSpacecraft().equalsIgnoreCase(spacecraft) && astronaut.getStatus()== AstronautStatus.ACTIVE)
                .forEach(astronaut -> {
                    System.out.printf("[#%d] %s | %s | %s | exp=%d%n",
                            astronaut.getId(),
                            astronaut.getName(),
                            astronaut.getSpacecraft(),
                            astronaut.getStatus(),
                            astronaut.getExperienceLevel());
                });
    }






}
