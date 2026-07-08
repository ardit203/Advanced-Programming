package Exercises._01_OOP.Bank;

public abstract class Account {
    protected static int SERIAL_NUMBER = 1;
    protected String name;
    protected int accountNumber;
    protected double balance;


    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = SERIAL_NUMBER++;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            return;
        }
        balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        String gClass = getClass().toString();
        String [] parts = gClass.split("\\.");

        return String.format("%d - %s - $%.2f -- %s\n", accountNumber, name, balance, parts[parts.length - 1]);
    }
}
