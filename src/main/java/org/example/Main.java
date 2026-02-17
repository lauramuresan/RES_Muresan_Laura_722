package org.example;

import org.example.Controller.ConsoleController;
import org.example.Model.Astronaut;
import org.example.Model.MissionEvent;
import org.example.Model.Supply;
import org.example.Repository.AstronautRepository;
import org.example.Repository.IRepository;
import org.example.Repository.MissionEventRepository;
import org.example.Repository.SupplyRepository;
import org.example.Service.AstronautService;
import org.example.Service.MissionEventService;
import org.example.Service.SupplyService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IRepository<Astronaut, Integer> astronautRepository = new AstronautRepository("astronauts.json");
        IRepository<MissionEvent,Integer> missionEventRepository = new MissionEventRepository("events.json");
        IRepository<Supply, Integer> supplyRepository = new SupplyRepository("supplies.json");

        AstronautService astronautService = new AstronautService(astronautRepository);
        SupplyService supplyService = new SupplyService(supplyRepository);
        MissionEventService missionEventService = new MissionEventService( missionEventRepository);


        ConsoleController controller = new ConsoleController (astronautService,missionEventService,supplyService);
        controller.start();
    }
}