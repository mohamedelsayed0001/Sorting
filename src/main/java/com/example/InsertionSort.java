package com.example;

import java.util.Arrays;

public class InsertionSort implements SortingStrategy {

    @Override
    public void sort(int[] array, boolean showSteps) {
        int length = array.length;
        for(int i=1; i<length;i++){
            int target = array[i];
            int j = i - 1;

            // Shift elements to the right
            while (j >= 0 && array[j] > target) {
                array[j + 1] = array[j];
                j--;
            }

            // Insert target at the correct position
            array[j + 1] = target;

            // Display array if showSteps is true
            if (showSteps) {
                System.out.println("After inserting " + target + ":\n" + Arrays.toString(array));
            }
        }
    }
}