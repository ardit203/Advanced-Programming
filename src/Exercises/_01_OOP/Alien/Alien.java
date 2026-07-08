package Exercises._01_OOP.Alien;


public abstract class Alien {
    private int health;
    String name;

    public Alien(int health, String name) {
        this.health = health;
        this.name = name;
    }

    public abstract int getDamage();

    @Override
    public String toString() {
        String gClass = getClass().toString();
        String [] parts = gClass.split("\\.");

        return String.format("%d - %s --- %s\n", health, name, parts[parts.length - 1]);
    }
}
