package com.multithreading.demo12;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Re-entrant locks
 */
public class Runner {

    private int count;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting...");
        condition.await();
        System.out.println("Woken up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press the return key.");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");

        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }

    private void increment() {
        IntStream.range(0, 10000).forEach(i -> count++);
    }
}
