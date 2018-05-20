package com.parrallel.computing.basic;

public class Synchronization {

    private static int COUNTER = 0;

    private static void process() {
        final Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                increment();
            }
        });

        final Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void increment() {
        ++COUNTER;
    }

    public static void main(String[] args) {
        process();

        System.out.println(COUNTER);
    }
}
