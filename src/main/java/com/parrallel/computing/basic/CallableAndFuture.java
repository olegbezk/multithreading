package com.parrallel.computing.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ProcessorCF implements Callable<String> {

    private int id;

    ProcessorCF(final int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id: " + id;
    }
}

public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Future<String> future = executorService.submit(new ProcessorCF(i + 1));
            futures.add(future);
        }

        futures.forEach(fs -> {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }
}
