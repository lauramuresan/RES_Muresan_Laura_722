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
}
