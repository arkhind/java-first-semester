package Multithreading;

public class BankAccount {
    private final int id;
    private int balance;

    public BankAccount(int id, int initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getId() {
        return id;
    }
}