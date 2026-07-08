package Exam.SecondMidtermExam.Task14;

import java.util.Map;
import java.util.TreeMap;

public class Window {
    private String name;
    private Map<Integer, Component> components;

    public Window(String name) {
        this.name = name;
        this.components = new TreeMap<>();
    }

    public void addComponent(int position, Component component) {
        Component c = components.putIfAbsent(position, component);

        if (c != null) {
            throw new InvalidPositionException(position);
        }
    }

    public void changeColor(int weight, String color) {
        components.values().forEach(c -> c.changeColor(weight, color));
    }

    void swichComponents(int pos1, int pos2) {
        Component p1 = components.get(pos1);
        Component p2 = components.get(pos2);

        components.put(pos1, p2);
        components.put(pos2, p1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW ").append(name).append("\n");

        components.forEach((k, v) -> {
            sb.append(k).append(":").append(v.print(0));
        });
        return sb.toString();
    }
}
