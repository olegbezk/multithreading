package com.parrallel.computing.sum.problem;

public class ParallelSum {

    private ParallelWorker[] sums;

    private int numberOfThreads;

    public ParallelSum(final int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.sums = new ParallelWorker[numberOfThreads];
    }

    public int sum(final int[] nums) {
        int size = (int) Math.ceil(nums.length * 1.0 / numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            sums[i] = new ParallelWorker(nums, i * size, (i + 1) * size);
            sums[i].start();
        }

        try {
            for (ParallelWorker worker : sums) {
                worker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;

        for (ParallelWorker worker : sums) {
            total += worker.getPartialSum();
        }

        return total;
    }
}
