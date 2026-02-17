package org.example.Service;

import org.example.Model.Astronaut;
import org.example.Model.AstronautStatus;
import org.example.Repository.IRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void displayAstronautsSortedByExperienceAndName() {
        repo.findAll().stream()
                .sorted((a1, a2) -> {
                    int expComparison = Integer.compare(a2.getExperienceLevel(), a1.getExperienceLevel());
                    if (expComparison != 0) {
                        return expComparison;
                    }
                    return a1.getName().compareToIgnoreCase(a2.getName());
                })
                .forEach(astronaut -> {
                    System.out.printf("[#%d] %s | %s | %s | exp=%d%n",
                            astronaut.getId(),
                            astronaut.getName(),
                            astronaut.getSpacecraft(),
                            astronaut.getStatus(),
                            astronaut.getExperienceLevel());
                });
    }


    public void saveAstronautsSortedByExperienceAndNameToFile() {
        List<Astronaut> sortedAstronauts = repo.findAll().stream()
                .sorted((a1, a2) -> {
                    int expComparison = Integer.compare(a2.getExperienceLevel(), a1.getExperienceLevel());
                    if (expComparison != 0) {
                        return expComparison;
                    }
                    return a1.getName().compareToIgnoreCase(a2.getName());
                })
                .toList();

        try (PrintWriter pw = new PrintWriter(new FileWriter("astronauts_sorted.txt"))) {
            for (int i = sortedAstronauts.size() - 1; i >= 0; i--) {
                Astronaut astronaut = sortedAstronauts.get(i);
                pw.printf("[#%d] %s | %s | %s | exp=%d%n",
                        astronaut.getId(),
                        astronaut.getName(),
                        astronaut.getSpacecraft(),
                        astronaut.getStatus(),
                        astronaut.getExperienceLevel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
