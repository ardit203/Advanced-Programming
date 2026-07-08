package Exercises._01_OOP.Bank;

import java.util.Random;
import java.util.Scanner;

public class Tester {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] parts = line.split("\\s++");
        String name = parts[0];
        int maxAccounts = Integer.parseInt(parts[1]);
        Bank bank = new Bank(name, maxAccounts);

        int numAccounts = Integer.parseInt(scanner.nextLine());
        Random random = new Random();
        for (int i = 0; i < numAccounts; i++) {
            line = scanner.nextLine();
            parts = line.split("\\s++");
            int rnd = random.nextInt(0, 12);
            if (rnd % 2 == 0) {
                bank.addAccount(new InterestCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            }else if (rnd % 3 == 0){
                bank.addAccount(new PlatinumCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            }else {
                bank.addAccount(new NonInterestCheckingAccount(parts[0], Double.parseDouble(parts[1])));
            }
        }

        System.out.println(bank);

        System.out.println(bank);
        System.out.println(bank.totalAssets());
        System.out.println("----------------------------------------------------------");
        bank.addInterest();
        System.out.println(bank);
        System.out.println(bank.totalAssets());


    }
}
