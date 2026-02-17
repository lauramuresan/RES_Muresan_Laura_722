package org.example.Service;

import org.example.Model.Supply;
import org.example.Repository.IRepository;

import java.util.Arrays;
import java.util.List;

public class SupplyService {

    private final IRepository<Supply, Integer> repo;

    public SupplyService(IRepository<Supply, Integer> repo) {
        this.repo = repo;
    }

    public List<Supply> getAll() {
        return repo.findAll();
    }


}
