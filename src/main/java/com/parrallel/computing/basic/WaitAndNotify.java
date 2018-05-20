package com.parrallel.computing.basic;

class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("In producer method.");
            wait();
            System.out.println("Again in producer method.");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("In consumer method.");
            notify();
            Thread.sleep(3000);
        }
    }
}

public class WaitAndNotify {

    public static void main(String[] args) {
        Processor processor = new Processor();

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

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
