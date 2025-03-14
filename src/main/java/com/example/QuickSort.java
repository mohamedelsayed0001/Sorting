package com.example;
import java.util.Random;
public class QuickSort implements SortingStrategy {
    private Random rand  ;
    @Override
    public void sort(int[] array, boolean showSteps) {
        rand = new Random();
        quickSort(array, 0, array.length - 1);
    }
    private void quickSort(int []array,int low,int high){
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1); 
            quickSort(array, pivotIndex + 1, high); 
        }
    }
    private int partition(int[] array, int low, int high) {

        int randomPivotIndex = low + rand.nextInt(high - low + 1);
        int temp = array[randomPivotIndex];
        array[randomPivotIndex] = array[high];
        array[high] = temp;

        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Swap array[i+1] and array[high] (pivot)
        temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
    
}
