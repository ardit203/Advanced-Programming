package Exam.SecondMidtermExam.Task23;

import java.util.HashMap;
import java.util.Map;

public class Audition {
    private Map<String, City> cities;

    public Audition() {
        this.cities = new HashMap<>();
    }

    public void addParticpant(String city, String code, String name, int age) {
        cities.computeIfAbsent(city, City::new).addParticpant(code, name, age);
    }

    public void listByCity(String city) {
        City c = cities.get(city);
        c.printSortedByNameAndAge();
    }
}
