package com.parrallel.computing.fork.join.framework.recursive.merge.sort;

import java.util.Arrays;

public class SequentialMergeSort {

    private int[] nums;

    public SequentialMergeSort(int[] nums) {
        this.nums = nums;
    }

    public void mergeSort(int[] nums) {

        if (nums.length <= 1) {
            return;
        }

        int middleIndex = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, nums);
    }

    private void merge(int[] leftSubArray, int[] rightSubArray, int[] originalArray) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSubArray.length && j < rightSubArray.length) {
            if (leftSubArray[i] < rightSubArray[j]) {
                originalArray[k++] = leftSubArray[i++];
            } else {
                originalArray[k++] = rightSubArray[j++];
            }
        }

        while (i < leftSubArray.length)
            originalArray[k++] = leftSubArray[i++];

        while (j < rightSubArray.length)
            originalArray[k++] = rightSubArray[j++];
    }

}
