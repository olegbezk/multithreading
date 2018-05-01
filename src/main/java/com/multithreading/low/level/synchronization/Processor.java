package com.multithreading.low.level.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * using low-level synchronization
 */
public class Processor {

    private static final int LIMIT = 10;

    private List<Integer> integers = new ArrayList<>();

    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (integers.size() == LIMIT) {
                    lock.wait();
                }
                integers.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (integers.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is: " + integers.size());
                int value = integers.remove(0);
                System.out.println("; value is: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}
