package com.multithreading.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Thread pools
 */
class Processor implements Runnable {

    private int id;

    Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + id);
    }
}

public class App {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 5).mapToObj(Processor::new).forEach(executor::submit);

        executor.shutdown();

        System.out.println("All tasks submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}
