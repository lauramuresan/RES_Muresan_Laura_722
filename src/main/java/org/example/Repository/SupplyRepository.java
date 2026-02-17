package org.example.Repository;

import org.example.Model.Supply;

public class SupplyRepository extends FileRepository<Supply, Integer> {

    public SupplyRepository(String filename) {
        super(filename,Supply.class);
    }


}