package com.parrallel.computing.fork.join.framework.recursive.maximum.finding;

public class SequentialMaxFinding {

    // O(N)
    public int sequentialMaxFinding(final int[] nums, int highIndex) {
        int max = nums[0];
        for (int i = 0; i < highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
