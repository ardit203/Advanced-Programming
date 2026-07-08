package Exercises._03_Generics.Box;

public class MyClass {
    private String name;

    public MyClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private String description;

    @Override
    public String toString() {
        return name + " " + description;
    }
}
