package Exam.SecondMidtermExam.Task14;


import java.util.Set;
import java.util.TreeSet;

public class Component implements Comparable<Component> {
    private String color;
    private int weight;
    private Set<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.components = new TreeSet<>();
    }

    public void addComponent(Component component){
        components.add(component);
    }

    public void changeColor(int weight, String color){
        if(this.weight < weight){
            this.color = color;
        }

        components.forEach(c -> c.changeColor(weight, color));
    }

    public String print(int level){
        StringBuilder sb = new StringBuilder();
        String lines = "-".repeat(level * 3);
        sb.append(lines).append(weight).append(":").append(color).append("\n");
        components.forEach(c -> sb.append(c.print(level + 1)));
        return sb.toString();
    }

    @Override
    public int compareTo(Component other) {
        int compare1 = Integer.compare(this.weight, other.weight);
        if(compare1 == 0){
            return this.color.compareTo(other.color);
        }
        return compare1;
    }
}
