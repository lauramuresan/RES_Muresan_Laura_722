package org.example.Controller;

import org.example.Service.AstronautService;
import org.example.Service.MissionEventService;
import org.example.Service.SupplyService;

import java.util.Scanner;

public class ConsoleController {

    private final AstronautService as;
    private final MissionEventService mes;
    private final SupplyService ss;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleController(AstronautService as, MissionEventService mes, SupplyService ss) {
        this.as = as;
        this.mes = mes;
        this.ss = ss;
    }

    public void start() {
        System.out.println("astronaut  loaded: " + as.getAll().size());
        System.out.println("mission events loaded: " + mes.getAll().size());
        System.out.println("supplies loaded: " + ss.getAll().size());

        while (true) {
            System.out.println("\nSelect:4.sorted astronauts in file, 0.Exit");
            System.out.println("1. alle astronauten");
            System.out.println("2. filtered astronauts");
            System.out.println("3. sorted astronauts");
            System.out.println("4.sorted astronauts in file");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> as.displayAllAstronauts();
                case "2" -> {
                    System.out.print("Input spacecraft: ");
                    as.displayActiveAstronautsBySpacecraft(sc.nextLine());
                }

                case "3" -> {
                    as.displayAstronautsSortedByExperienceAndName();
                }

                case "4" -> {
                    as.saveAstronautsSortedByExperienceAndNameToFile();
                }
                case "0" -> {
                    return;

                }
            }
        }
    }
}