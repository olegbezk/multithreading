package com.parrallel.computing.basic;

import java.util.ArrayList;
import java.util.List;

class ProcessorC {

    List<Integer> items = new ArrayList<>();

    private final int LIMIT = 5;

    private final int BOTTOM = 0;

    private final Object lock = new Object();

    private int value = 0;

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (items.size() == LIMIT) {
                    System.out.println("Waiting for removing items from list.");
                    lock.wait();
                } else {
                    System.out.println("Adding value to the list: " + value);
                    items.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (items.size() == BOTTOM) {
                    System.out.println("Waiting for adding items to the list.");
                    lock.wait();
                } else {
                    System.out.println("Removing items from the list: " + items.remove(--value));
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}

public class ProducerAndConsumer {

    public static void main(String[] args) {
        ProcessorC processor = new ProcessorC();

        Thread thread1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
