package com.parrallel.computing.fork.join.framework.recursive.maximum.finding;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

    public static int THRESHOLD = 0;

    public static void main(String[] args) {
        int[] nums = initializeNums();

        THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();

        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();

        long start = System.currentTimeMillis();
        System.out.println("Max: " + sequentialMaxFinding.sequentialMaxFinding(nums, nums.length));
        System.out.println("Time seq taken: " + (System.currentTimeMillis() - start) + "ms");

        System.out.println();

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);

        start = System.currentTimeMillis();
        System.out.println("Max: " + pool.invoke(parallelMaxFinding));
        System.out.println("Time parallel taken: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static int[] initializeNums() {

        final Random random = new Random();
        final int[] ints = new int[100000000];

        for (int i = 0; i < 10000; i++) {
            ints[i] = random.nextInt(1000);
        }
        return ints;
    }
}
