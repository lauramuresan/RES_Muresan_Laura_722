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
        IRepository<Astronaut, Integer> playerRepository = new AstronautRepository("astronauts.json");
        IRepository<MissionEvent,Integer> matchPerformanceRepository = new MissionEventRepository("events.json");
        IRepository<Supply, Integer> medicalRecordRepository = new SupplyRepository("supplies.json");

        AstronautService playerService = new AstronautService(playerRepository);
        SupplyService matchPerformanceService = new SupplyService(medicalRecordRepository);
        MissionEventService medicalRecordService = new MissionEventService( matchPerformanceRepository);


        ConsoleController controller = new ConsoleController (playerService,medicalRecordService ,matchPerformanceService);
        controller.start();
    }
}