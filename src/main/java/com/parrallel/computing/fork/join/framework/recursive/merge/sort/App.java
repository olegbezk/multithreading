package com.parrallel.computing.fork.join.framework.recursive.merge.sort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {

        int[] nums = initializeNums();
        SequentialMergeSort mergeSort = new SequentialMergeSort(nums);

        long start = System.currentTimeMillis();
        mergeSort.mergeSort(nums);
        System.out.println("Time taken with sequential sort: " + (System.currentTimeMillis() - start) + "ms");

        ParallelMergeSortTask rootTask = new ParallelMergeSortTask(nums);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        start = System.currentTimeMillis();
        pool.invoke(rootTask);
        System.out.println("Time taken with parallel sort: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static int[] initializeNums() {

        Random random = new Random();

        int[] nums = new int[100000000];

        for (int i = 0; i < 100000000; ++i)
            nums[i] = random.nextInt(100);

        return nums;
    }
}
