package Book.Ch1_OOP_in_Java.Problem1_2;

import java.util.Objects;

public class Account {
    private static long ID_TRACKER = 0;
    private String name;
    private long id;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.id = ID_TRACKER++;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Account a = (Account) obj;
        return Objects.equals(this.id, a.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, balance);
    }
}
