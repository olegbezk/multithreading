package com.parrallel.computing.concurrent.collections;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerCL implements Runnable {

    private int id;

    private CountDownLatch countDownLatch;

    private Random random;

    public WorkerCL(final int id, final CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork() {
        System.out.println("thread with id: " + this.id + " starts working.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountdownLatches {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i < 6; i++) {
            executorService.execute(new WorkerCL(i, countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All prerequisites were finished...");

        executorService.shutdown();
    }
}
