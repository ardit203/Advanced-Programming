package Book.Ch1_OOP_in_Java.Problem1_2;

import java.util.Scanner;

public class BankTester {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(),
                    Double.parseDouble(jin.nextLine()));
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String description = jin.nextLine();
                    double amount = Double.parseDouble(jin.nextLine());
                    double parameter = Double.parseDouble(jin.nextLine());
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(description, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + t.getAmount());
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + bank.totalProvision());
                    System.out.println("Total transfers: " + bank.totalTransfers());
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description,
                                              int from_idx, int to_idx,
                                              double amount, double o,
                                              Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(
                        bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(
                        bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, (int) o);
        }
        return null;
    }
}