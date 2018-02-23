package com.multithreading.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Using multiple locks and synchronized code blocks
 */
public class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();

    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        synchronize(list1, lock1);
    }

    public void stageTwo() {
        synchronize(list2, lock2);
    }

    private void synchronize(List<Integer> list, Object lock) {
        synchronized (lock) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(random.nextInt(100));
        }
    }

    public void process() {
        IntStream.range(0, 1000).forEach(i -> {
            stageOne();
            stageTwo();
        });
    }

    public void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        final Thread thread1 = new Thread(this::process);

        final Thread thread2 = new Thread(this::process);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end - start));
        System.out.println("List 1: " + list1.size() + "; List 2: " + list2.size());
    }
}
