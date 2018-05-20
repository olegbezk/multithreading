package com.parrallel.computing.basic;

public class SynchronizedBlocks {

    private static int count1 = 0;

    private static int count2 = 0;

    private static Object lock1 = new Object();

    private static Object lock2 = new Object();

    public static void add() {
        synchronized (lock1) {
            ++count1;
        }
    }

    public static void addAgain() {
        synchronized (lock2) {
            ++count2;
        }
    }

    public static void compute() {
        for (int i = 0; i < 100; i++) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) {

        final Thread thread1 = new Thread(SynchronizedBlocks::compute);
        final Thread thread2 = new Thread(SynchronizedBlocks::compute);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count 1: " + count1 + " " + "Count 2: " + count2);
    }
}
