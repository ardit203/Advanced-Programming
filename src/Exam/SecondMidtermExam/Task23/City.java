package Exam.SecondMidtermExam.Task23;
import java.util.HashMap;
import java.util.Map;

public class City {
    private String name;
    private Map<String, Participant> participants;

    public City(String name) {
        this.name = name;
        this.participants = new HashMap<>();
    }

    public void addParticpant(String code, String name, int age){
        participants.putIfAbsent(code, new Participant(code, name, age));
    }

    public void printSortedByNameAndAge(){
        participants.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(e.getValue()));
    }
}
