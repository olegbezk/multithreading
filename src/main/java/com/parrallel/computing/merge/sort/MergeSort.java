package com.parrallel.computing.merge.sort;

public class MergeSort {

    private int[] nums;
    private int[] tempArray;

    public MergeSort(final int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    public void showResult() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public void merge(final int low, final int middle, final int high) {

        // Copy nums[i] -> temp[i]
        for (int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        // Copy the smallest nums from both sides back to the original array
        while ((i <= middle) && (j <= high)) {
            if (tempArray[i] <= tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            k++;
        }

        // copy the rest left side of array into the target array
        while (i <= middle) {
            nums[k] = tempArray[i];
            k++;
            i++;
        }

        // copy the rest right side of array into the target array
        while (j <= high) {
            nums[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
