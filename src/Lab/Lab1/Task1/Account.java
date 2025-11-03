package Lab.Lab1.Task1;

public class Account {
    private static long ID_COUNT = 0;
    private String name;
    private long id;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.id = ID_COUNT++;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %.2f$\n", name, balance);
    }
}