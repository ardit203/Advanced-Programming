package Exercises._01_OOP.Alien;

public class MarshmallowMan extends Alien {
    public MarshmallowMan(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage() {
        return 1;
    }
}
