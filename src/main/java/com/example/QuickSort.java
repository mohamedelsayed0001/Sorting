package com.example;

import java.util.*;

public class QuickSort implements SortingStrategy {
    private Random rand;

    @Override
    public String[] sort(int[] array) {
        rand = new Random();
        List<String> steps = new ArrayList<>();
        quickSort(array, 0, array.length - 1, steps);
        return steps.toArray(new String[0]);
    }

    private void quickSort(int[] array, int low, int high, List<String> steps) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, steps);
            quickSort(array, low, pivotIndex - 1, steps);
            quickSort(array, pivotIndex + 1, high, steps);
        }
    }

    private int partition(int[] array, int low, int high, List<String> steps) {
        int randomPivotIndex = low + rand.nextInt(high - low + 1);
        int temp = array[randomPivotIndex];
        array[randomPivotIndex] = array[high];
        array[high] = temp;

        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        if(array.length <= 1000) {
            steps.add("After partitioning with pivot " + pivot + ": " + Arrays.toString(array));
        }

        return i + 1;
    }
}
