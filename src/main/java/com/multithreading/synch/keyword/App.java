package com.multithreading.synch.keyword;

/**
 * Using synchronized keyword
 */
public class App {

    private int count = 0;

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    private synchronized void increment() {
        count++;
    }

    private void doWork() {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }
}
