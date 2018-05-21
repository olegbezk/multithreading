package com.parrallel.computing.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    private static int counter = 0;

    private static Lock lock = new ReentrantLock();

    private static void increment() {

        lock.lock();

        try {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(Locks::increment);
        Thread thread2 = new Thread(Locks::increment);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter is: " + counter);
    }
}
