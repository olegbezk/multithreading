package com.multithreading.demo13;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account acc1 = new Account();

    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {
        Random random = new Random();

        acquireLocks(lock1, lock2);

        try {
            for (int i = 0; i < 1000; i++) {
                Account.transfer(acc1, acc2, random.nextInt(100));
            }
        } finally {
            lock1.unlock();
            lock2.unlock();
        }

    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();

        acquireLocks(lock2, lock1);

        try {
            for (int i = 0; i < 1000; i++) {
                Account.transfer(acc1, acc2, random.nextInt(100));
            }
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + acc1.getBalance());
        System.out.println("Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            //Acquire the lock
            boolean gotFirstLock = true;
            boolean gotSecondLock = true;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }
            finally {
                if (gotFirstLock && gotSecondLock) {
                    //noinspection ReturnInsideFinallyBlock
                    return;
                }

                if (gotFirstLock) {
                    firstLock.unlock();
                }

                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }

            // Lock not acquired
            Thread.sleep(1);
        }
    }
}
