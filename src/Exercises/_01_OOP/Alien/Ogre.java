package Exercises._01_OOP.Alien;

public class Ogre extends Alien {
    public Ogre(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage() {
        return 6;
    }
}
