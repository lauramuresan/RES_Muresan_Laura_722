package org.example.Repository;

import org.example.Model.MissionEvent;

public class MissionEventRepository extends FileRepository<MissionEvent, Integer> {

    public MissionEventRepository(String filename) {
        super(filename,MissionEvent.class);
    }


}