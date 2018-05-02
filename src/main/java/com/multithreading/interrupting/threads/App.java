package com.multithreading.interrupting.threads;

import java.util.Random;

/**
 * Interrupting threads
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting.");

        Thread th1 = new Thread(() -> {
            Random random = new Random();

            for (int i = 0; i < 1E8; i++) {

//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Interrupted!");
//                    break;
//                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                    break;
                }

                Math.sin(random.nextDouble());
            }
        });

        th1.start();

        Thread.sleep(500);

        th1.interrupt();

        th1.join();

        System.out.println("Finished.");
    }
}
