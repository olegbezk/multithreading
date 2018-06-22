package com.parrallel.computing.fork.join.framework.recursive.maximum.finding;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer> {

    private int[] nums;

    private int lowIndex;

    private int highIndex;

    public ParallelMaxFinding(final int[] nums, final int lowIndex, final int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Integer compute() {
        if (highIndex - lowIndex < App.THRESHOLD) {
            return sequentialMaxFinding();
        } else {
            int middleIndex = (lowIndex + highIndex) / 2;

            final ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
            final ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex, highIndex);

            invokeAll(task1, task2);

            return Math.max(task1.join(), task2.join());
        }
    }

    private int sequentialMaxFinding() {
        int max = nums[0];
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
