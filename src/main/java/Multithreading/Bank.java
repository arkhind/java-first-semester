package Multithreading;

import com.mipt.BankAccount;

public class Bank {

    public static void sendToAccountDeadLock(BankAccount from, BankAccount to, int amount) {
        synchronized (from) {
            // Искусственная задержка чтобы увеличить шанс дедлока
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (to) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }

    public static void sendToAccount(BankAccount from, BankAccount to, int amount) {
        if (from == null || to == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        if (from.getId() == to.getId()) {
            return;
        }

        BankAccount first = from.getId() < to.getId() ? from : to;
        BankAccount second = from.getId() < to.getId() ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}