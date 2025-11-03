package Exercises._01_OOP.Bank;

public class PlatinumCheckingAccount extends InterestCheckingAccount implements InterestBearingAccount {

    public PlatinumCheckingAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public void addInterest() {
        deposit(getBalance() * INTEREST_RATE * 2);
    }
}
