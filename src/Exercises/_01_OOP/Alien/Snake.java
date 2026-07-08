package Exercises._01_OOP.Alien;

public class Snake extends Alien{

    public Snake(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage() {
        return 10;
    }
}
