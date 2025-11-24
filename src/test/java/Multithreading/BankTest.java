package Multithreading;


import com.mipt.BankAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void testSendToAccount() throws InterruptedException {
        BankAccount acc1 = new BankAccount(1, 1000);
        BankAccount acc2 = new BankAccount(2, 1000);

        Thread t1 = new Thread(() -> Bank.sendToAccount(acc1, acc2, 100));
        Thread t2 = new Thread(() -> Bank.sendToAccount(acc2, acc1, 100));

        t1.start();
        t2.start();
        t1.join(2000);
        t2.join(2000);

        assertEquals(2000, acc1.getBalance() + acc2.getBalance());
    }

    @Test
    public void testSendToAccountInvalidParams() {
        BankAccount acc1 = new BankAccount(1, 1000);
        assertThrows(IllegalArgumentException.class, () -> {
            Bank.sendToAccount(acc1, null, 100);
        });
    }

    @Test
    public void testSendToAccountInsufficientFunds() {
        BankAccount acc1 = new BankAccount(1, 50);
        BankAccount acc2 = new BankAccount(2, 1000);

        Bank.sendToAccount(acc1, acc2, 100);
        assertEquals(50, acc1.getBalance());
        assertEquals(1000, acc2.getBalance());
    }

    @Test
    public void testSendToAccountDeadlock() throws InterruptedException {
        BankAccount acc1 = new BankAccount(1, 10000);
        BankAccount acc2 = new BankAccount(2, 10000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Bank.sendToAccountDeadLock(acc1, acc2, 1);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Bank.sendToAccountDeadLock(acc2, acc1, 1);
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(500);

        boolean bothAlive = t1.isAlive() && t2.isAlive();

        t1.interrupt();
        t2.interrupt();

        t1.join(1000);
        t2.join(1000);

        System.out.println("Deadlock occurred: " + bothAlive);
    }

    @Test
    public void testSendToAccountSameAccount() {
        BankAccount acc1 = new BankAccount(1, 1000);

        Bank.sendToAccount(acc1, acc1, 100);
        assertEquals(1000, acc1.getBalance());
    }
}