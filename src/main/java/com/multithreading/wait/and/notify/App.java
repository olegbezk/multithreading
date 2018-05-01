package com.multithreading.wait.and.notify;

/**
 * Wait and notify
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        Processor processor = new Processor();

        final Thread thread1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread thread2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
