package Exam.SecondMidterm.Task14;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Component implements Comparable<Component> {
    private String color;
    private int weight;
    private Set<Component> innerComponents;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.innerComponents = new TreeSet<>();
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public void addComponent(Component component){
        innerComponents.add(component);
    }

    @Override
    public int compareTo(Component other) {
        return Comparator.comparingInt(Component::getWeight)
                .reversed()
                .thenComparing(Component::getColor)
                .compare(this, other);
    }
}
