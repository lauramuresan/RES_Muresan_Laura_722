package org.example.Repository;

import org.example.Model.Astronaut;

public class AstronautRepository extends FileRepository<Astronaut, Integer> {

    public AstronautRepository(String filename) {
        super(filename,Astronaut.class);
    }


}