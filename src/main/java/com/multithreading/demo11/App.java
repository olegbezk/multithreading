package com.multithreading.demo11;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Thread th1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread th2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();
    }
}
