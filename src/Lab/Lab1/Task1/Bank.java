package Lab.Lab1.Task1;

import java.lang.constant.Constable;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Bank {
    private String name;
    private Account[] accounts;
    private double totalTransfers;
    private double totalProvision;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = Arrays.copyOf(accounts, accounts.length);
    }

    public boolean makeTransaction(Transaction t) {
        Account from = find(t.getFromId());
        Account to = find(t.getToId());

        if (from != null && to != null) {
            double fromBalance = from.getBalance();
            double toBalance = to.getBalance();
            double amount = t.getAmount();
            double provision;

            if (t.getDescription().equals("FlatAmount")) {
                FlatAmountProvisionTransaction fa = (FlatAmountProvisionTransaction) t;
                provision = fa.getFlatAmount();
            } else {
                FlatPercentProvisionTransaction fp = (FlatPercentProvisionTransaction) t;
                provision = fp.getPercent() / 100.0 * amount;
            }

            if (provision + amount <= fromBalance) {
                if (from.getId() == to.getId()) {
                    fromBalance = fromBalance - provision;
                    toBalance = toBalance - provision;
                } else {
                    fromBalance -= provision + amount;
                    toBalance += amount;
                }
                from.setBalance(fromBalance);
                to.setBalance(toBalance);
                this.totalTransfers += amount;
                this.totalProvision += provision;

                return true;
            }
        }
        return false;
    }

    private Account find(long id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public double totalTransfers() {
        return totalTransfers;
    }

    public double totalProvision() {
        return totalProvision;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for (Account account : accounts) {
            sb.append(account.toString());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(totalTransfers, bank.totalTransfers) == 0
                && Double.compare(totalProvision, bank.totalProvision) == 0
                && Objects.equals(name, bank.name) && Objects.deepEquals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), totalTransfers, totalProvision);
    }

    /*Additional Requirement*/
    public void forEachConditional(Predicate<Account> predicate, Consumer<Account> consumer) {
        for (Account account : accounts) {
            if (predicate.test(account)) {
                consumer.accept(account);
            }
        }
    }
}
