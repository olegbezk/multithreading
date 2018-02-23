package com.multithreading.demo9;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Blocking queue & producer - consumer pattern
 */
public class App {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private static void producer() throws InterruptedException {

        Random random = new Random();

        while (true) {
            queue.put(random.nextInt());
        }
    }

    private static void consumer() throws InterruptedException {

        Random random = new Random();

        while (true) {
            Thread.sleep(100);

            if (random.nextInt(10) == 0) {
                final Integer take = queue.take();

                System.out.println("Taken value: " + take + "; Queue size is: " + queue.size());
            }
        }
    }
}
