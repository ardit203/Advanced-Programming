package Exercises._01_OOP.Alien;

import java.util.Random;
import java.util.Scanner;

public class Tester {
    static void main() {
        Scanner scanner = new Scanner(System.in);


        int maxAliens = Integer.parseInt(scanner.nextLine());
        AlienPack alienPack = new AlienPack(maxAliens);

        int numAliens = Integer.parseInt(scanner.nextLine());
        Random random = new Random();

        for (int i = 0; i < numAliens; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s++");
            int rnd = random.nextInt();
            if (rnd % 2 == 0) {
                alienPack.addAlien(new Snake(Integer.parseInt(parts[0]), parts[1]), i);
            } else if (rnd % 3 == 0) {
                alienPack.addAlien(new Ogre(Integer.parseInt(parts[0]), parts[1]), i);
            } else {
                alienPack.addAlien(new MarshmallowMan(Integer.parseInt(parts[0]), parts[1]), i);
            }
        }
        System.out.println(alienPack);
        System.out.println(alienPack.calculateDamage());
    }


}
