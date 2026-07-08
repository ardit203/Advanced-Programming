package Exam.SecondMidtermExam.Task31;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Stadium {
    private String name;
    private Map<String, Sector> sectors;


    public Stadium(String name) {
        this.name = name;
        this.sectors = new HashMap<>();
    }

    public void createSectors(String[] sectorNames, int[] sizes) {
        for (int i = 0; i < sectorNames.length; i++) {
            sectors.putIfAbsent(sectorNames[i], new Sector(sectorNames[i], sizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) {
        sectors.get(sectorName).buyTicket(seat, type);
    }

    public void showSectors() {
        sectors.values()
                .stream()
                .sorted(Comparator.comparingInt(Sector::freeSeats).reversed().thenComparing(Sector::getCode))
                .forEach(System.out::println);
    }
}
