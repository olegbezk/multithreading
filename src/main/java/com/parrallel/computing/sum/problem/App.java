package com.parrallel.computing.sum.problem;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        Random random = new Random();
        SequentialSum sequentialSum = new SequentialSum();
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("number of processors: " + availableProcessors);

        int[] nums = new int[100000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        long start = System.currentTimeMillis();
        System.out.println("Sum = " + sequentialSum.sum(nums));
        long end = System.currentTimeMillis();

        System.out.println("Execution time for sequential summing = " + (end - start) + "ms");

        start = System.currentTimeMillis();
        ParallelSum parallelSum = new ParallelSum(availableProcessors);
        System.out.println("Parallel sum = " + parallelSum.sum(nums));
        System.out.println("Execution time for parallel summing = " + (System.currentTimeMillis() - start) + "ms");

    }
}
