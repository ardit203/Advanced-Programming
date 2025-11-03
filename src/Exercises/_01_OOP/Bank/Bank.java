package Exercises._01_OOP.Bank;


public class Bank {
    private String name;
    private Account[] accounts;
    private int totalAccounts;
    private int maxAccounts;

    public Bank(String name, int maxAccounts) {
        this.name = name;
        this.accounts = new Account[maxAccounts];
        this.maxAccounts = maxAccounts;
        this.totalAccounts = 0;
    }

    public void addAccount(Account a) {
        if (totalAccounts == maxAccounts) {
            return;
        }
        accounts[totalAccounts++] = a;
    }

    public double totalAssets() {
        double sum = 0;
        for (int i = 0; i < totalAccounts; i++) {
            sum += accounts[i].getBalance();
        }
        return sum;
    }

    public void addInterest() {
        for (Account account : accounts) {
            if (account instanceof InterestBearingAccount) {
                InterestBearingAccount a = (InterestBearingAccount) account;
                a.addInterest();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for (int i = 0; i < totalAccounts; i++) {
            sb.append(accounts[i]);
        }
        return sb.toString();
    }
}
