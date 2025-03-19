package com.example;

import java.util.*;

public class InsertionSort implements SortingStrategy {
    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public String getComplexity() {
        return "O(n^2)";
    }

    @Override
    public String[] sort(int[] array) {
        List<String> steps = new ArrayList<>();
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int target = array[i];
            int j = i - 1;

            // Shift elements to the right
            while (j >= 0 && array[j] > target) {
                array[j + 1] = array[j];
                j--;
            }

            // Insert target at the correct position
            array[j + 1] = target;

            if(length >= 99) {
                continue;
            }
            
            steps.add("After inserting " + target + ": " + Arrays.toString(array));
        }
        return steps.toArray(new String[0]);
    }
}
