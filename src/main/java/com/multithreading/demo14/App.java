package com.multithreading.demo14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Using Semaphore class
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        IntStream.range(0, 200).<Runnable>mapToObj(i -> () -> Connection.getInstance().connect()).forEach(executor::submit);

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
