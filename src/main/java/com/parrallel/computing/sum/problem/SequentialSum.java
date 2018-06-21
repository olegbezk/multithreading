package com.parrallel.computing.sum.problem;

public class SequentialSum {

    public int sum(final int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }
}
