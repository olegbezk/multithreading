package com.parrallel.computing.concurrent.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Firstworker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public Firstworker(final BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                blockingQueue.put(counter);
                System.out.println("Putting item into the queue: " + counter);
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker(final BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Taking item from the queue: " + blockingQueue.take());

                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueues {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        Firstworker firstworker = new Firstworker(blockingQueue);
        SecondWorker secondWorker = new SecondWorker(blockingQueue);

        new Thread(firstworker).start();
        new Thread(secondWorker).start();
    }
}
