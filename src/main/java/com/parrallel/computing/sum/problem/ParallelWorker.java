package com.parrallel.computing.sum.problem;

public class ParallelWorker extends Thread {

    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelWorker(final int[] nums, final int low, final int high) {
        this.nums = nums;
        this.low = low;
        this.high = Math.min(high, nums.length);
    }

    public int getPartialSum() {
        return this.partialSum;
    }

    @Override
    public void run() {
        partialSum = 0;

        for (int i = low; i < high; i++) {
            partialSum += nums[i];
        }
    }
}
