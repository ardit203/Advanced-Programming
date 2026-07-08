package Lab.Lab1.Task1;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private String name;
    private Account[] accounts;
    private double totalTransfers;
    private double totalProvisions;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = Arrays.copyOf(accounts, accounts.length);
    }

    public boolean makeTransaction(Transaction t) {
        Account from = findAccount(t.getFromId());
        Account to = findAccount(t.getToId());

        double amount = t.getAmount();
        double provision = t.getProvision();

        if (!checkConditions(from, to, amount + provision)) {
            return false;
        }

        double fromBalance = from.getBalance();
        double toBalance = to.getBalance();

        if (from.getId() == to.getId()) {
            fromBalance = toBalance = fromBalance - provision;
        } else {
            fromBalance -= amount + provision;
            toBalance += amount;
        }
        from.setBalance(fromBalance);
        to.setBalance(toBalance);

        totalTransfers += amount;
        totalProvisions += provision;
        return true;
    }

    private Account findAccount(long id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    private boolean checkConditions(Account from, Account to, double amount) {
        return from != null && to != null && from.getBalance() >= amount;
    }

    public double totalProvision() {
        return totalProvisions;
    }

    public double totalTransfers() {
        return totalTransfers;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for (Account account : accounts) {
            sb.append(account).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) &&
                Arrays.equals(accounts, bank.accounts) &&
                Objects.equals(totalTransfers, bank.totalTransfers) &&
                Objects.equals(totalProvisions, bank.totalProvisions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), totalTransfers, totalProvisions);
    }
}