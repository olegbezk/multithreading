package com.multithreading.demo12;

/**
 * Re-entrant locks
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();

        Thread th1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread th2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        runner.finished();
    }
}
