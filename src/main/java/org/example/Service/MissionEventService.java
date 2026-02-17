package org.example.Service;

import org.example.Model.MissionEvent;
import org.example.Repository.IRepository;

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
}
