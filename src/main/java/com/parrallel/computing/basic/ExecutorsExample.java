package com.parrallel.computing.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerE implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExecutorsExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new WorkerE());
        }
    }
}
