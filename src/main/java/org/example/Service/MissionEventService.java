package org.example.Service;

import org.example.Model.MissionEvent;
import org.example.Repository.IRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MissionEventService {

    private final IRepository<MissionEvent, Integer> repo;

    public MissionEventService(IRepository<MissionEvent, Integer> repo) {
        this.repo = repo;
    }

    public List<MissionEvent> getAll() {
        return repo.findAll();
    }


    public void calculateAndDisplayMissionEventPoints() {
        repo.findAll().stream().distinct()
                .limit(5).
                forEach(e -> {
                    int computedPoints = switch (e.getType()) {
                        case EVA -> e.getBasePoints() + 2 * e.getDay();
                        case SYSTEM_FAILURE -> e.getBasePoints() - 3 - e.getDay();
                        case SCIENCE -> e.getBasePoints() + (e.getDay() % 4);
                        case MEDICAL -> e.getBasePoints() - 2 * (e.getDay() % 3);
                        case COMMUNICATION -> e.getBasePoints() + 5;
                    };
                    System.out.printf("Event %d -> raw=%d -> computed=%d%n",
                            e.getId(), e.getBasePoints(), computedPoints);
                });


    }

    public void generateMissionReport() {
       try ( PrintWriter pw = new PrintWriter(new FileWriter("mission_report.txt"))) {
           repo.findAll().stream()
                   .collect(java.util.stream.Collectors.groupingBy(MissionEvent::getType, java.util.stream.Collectors.counting()))
                   .entrySet().stream()
                   .sorted((e1, e2) -> {
                       int countComparison = Long.compare(e2.getValue(), e1.getValue());
                       if (countComparison != 0) {
                           return countComparison;
                       }
                       return e1.getKey().name().compareToIgnoreCase(e2.getKey().name());
                   })
                   .forEach(entry -> pw.printf("%s -> %d%n", entry.getKey(), entry.getValue()));
       } catch (IOException e) {
           System.err.println("Error writing mission report: " + e.getMessage());
       }
    }
}
