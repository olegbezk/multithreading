package com.parrallel.computing.sum.problem;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        Random random = new Random();
        SequentialSum sequentialSum = new SequentialSum();

        int[] nums = new int[100000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        final long start = System.currentTimeMillis();
        System.out.println("Sum = " + sequentialSum.sum(nums));
        final long end = System.currentTimeMillis();

        System.out.println("Execution time = " + (end - start) + "ms");
    }
}
