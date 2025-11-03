package Exercises._01_OOP.Alien;

import java.util.Arrays;

public class AlienPack {
    private Alien [] aliens;

    public AlienPack(int maxAliens){
        this.aliens = new Alien[maxAliens];
    }

    public void addAlien(Alien alien, int index){
        if(index < 0 || index > aliens.length){
            return;
        }
        aliens[index] = alien;
    }

    public int calculateDamage(){
        int damage = 0;
        for (Alien alien : aliens) {
            if (alien == null){
                continue;
            }
            damage += alien.getDamage();
        }
        return damage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Alien alien : aliens) {
            if(alien != null){
                sb.append(alien);
            }
        }
        return sb.toString();
    }
}
