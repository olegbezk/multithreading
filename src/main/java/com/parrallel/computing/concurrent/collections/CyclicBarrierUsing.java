package com.parrallel.computing.concurrent.collections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerCB implements Runnable {

    private int id;

    private Random random;

    private CyclicBarrier cyclicBarrier;

    WorkerCB(final int id, final CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.random = new Random();
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID: " + id + " start task.");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with ID: " + id + " finished task.");

        try {
            cyclicBarrier.await();
            System.out.println("After await.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "WorkerCB{" +
                "id=" + this.id +
                ", random=" + this.random +
                ", cyclicBarrier=" + this.cyclicBarrier +
                '}';
    }
}

public class CyclicBarrierUsing {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("All tasks finished..."));

        for (int i = 1; i < 6; i++) {
            executorService.execute(new WorkerCB(i, cyclicBarrier));
        }
        executorService.shutdown();
    }
}
