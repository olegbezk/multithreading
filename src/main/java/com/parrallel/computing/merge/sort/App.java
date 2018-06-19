package com.parrallel.computing.merge.sort;

import java.util.Random;

public class App {

    public static Random random = new Random();

    private static final int AMOUNT_OF_NUMBERS = 200000000;

    public static void main(String[] args) {

        final int numOfThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads: " + numOfThreads + "\n");

        int[] numbers = createRandomArray(AMOUNT_OF_NUMBERS);

        //  O(N logN) complexity
        MergeSort mergeSort = new MergeSort(numbers);

        long startTime1 = System.currentTimeMillis();
        // will be faster then sequential sort on big data amounts
        mergeSort.parallelMergeSort(0, numbers.length -1, numOfThreads);
        long endTime1 = System.currentTimeMillis();

        System.out.printf("Time taken for 200 000 000 elements parallel =>  %6d ms \n", endTime1 - startTime1);
        System.out.println("\n");

        startTime1 = System.currentTimeMillis();
        mergeSort.mergeSort(0,numbers.length-1);
        endTime1 = System.currentTimeMillis();

        System.out.printf("Time taken for 200 000 000 elements sequential =>  %6d ms \n", endTime1 - startTime1);
        System.out.println("\n");
    }

    private static int[] createRandomArray(int amount) {
        int[] a = new int[amount];
        for (int i = 0; i < amount; i++) {
            a[i] = random.nextInt(100000);
        }
        return a;
    }
}
