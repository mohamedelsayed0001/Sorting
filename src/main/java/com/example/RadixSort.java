package com.example;

import java.util.Arrays;
import java.util.Random;

public class RadixSort implements SortingStrategy {
   public boolean showSteps;

   RadixSort(boolean showSteps) {
      this.showSteps = showSteps;
   }

   // Utility function to get maximum absolute value in arr[]
   private int getMax(int arr[], int n) {
      int mx = Math.abs(arr[0]);
      for (int i = 1; i < n; i++)
         if (Math.abs(arr[i]) > mx)
            mx = Math.abs(arr[i]);
      return mx;
   }

   // Counting Sort as a subroutine for Radix Sort
   private void countSort(int arr[], int n, int exp) {
      int output[] = new int[n];
      int count[] = new int[10];
      Arrays.fill(count, 0);

      // Store count of occurrences
      for (int i = 0; i < n; i++)
         count[(Math.abs(arr[i]) / exp) % 10]++;

      // Compute prefix sums to get positions
      for (int i = 1; i < 10; i++)
         count[i] += count[i - 1];

      // Build the output array
      for (int i = n - 1; i >= 0; i--) {
         output[count[(Math.abs(arr[i]) / exp) % 10] - 1] = arr[i];
         count[(Math.abs(arr[i]) / exp) % 10]--;
      }

      // Copy output array back to arr[]
      for (int i = 0; i < n; i++)
         arr[i] = output[i];

      if (this.showSteps) {
         System.out.print("After sorting for exp = " + exp + ": ");
         for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
         System.out.println();
      }
   }

   @Override
   public void sort(int arr[]) {
      int n = arr.length;
      if (n == 0)
         return;

      // Separate negatives and positives
      int[] negatives = Arrays.stream(arr).filter(x -> x < 0).toArray();
      int[] positives = Arrays.stream(arr).filter(x -> x >= 0).toArray();

      // Sort negatives based on absolute values
      int maxNeg = negatives.length > 0 ? getMax(negatives, negatives.length) : 0;
      for (int exp = 1; maxNeg / exp > 0; exp *= 10)
         countSort(negatives, negatives.length, exp);

      // Sort positives normally
      int maxPos = positives.length > 0 ? getMax(positives, positives.length) : 0;
      for (int exp = 1; maxPos / exp > 0; exp *= 10)
         countSort(positives, positives.length, exp);

      // Reverse negatives array to maintain order
      for (int i = 0, j = negatives.length - 1; i < j; i++, j--) {
         int temp = negatives[i];
         negatives[i] = negatives[j];
         negatives[j] = temp;
      }

      // Merge negatives first, then positives
      int index = 0;
      for (int num : negatives)
         arr[index++] = num;
      for (int num : positives)
         arr[index++] = num;
   }

   public static void main(String[] args) {
      RadixSort radixSort = new RadixSort(true);

      // Test Case 1: Sorted Array
      int[] sortedArray = { 1, 2, 3, 4, 5 };
      radixSort.sort(sortedArray);
      System.out.print("Sorted Array Test: ");
      printArray(sortedArray);

      // Test Case 2: Unsorted Array
      int[] unsortedArray = { 59, 53, 48, 81, 92 };
      radixSort.sort(unsortedArray);
      System.out.print("Unsorted Array Test: ");
      printArray(unsortedArray);

      // Test Case 3: Reverse Sorted Array
      int[] reverseSortedArray = { 129, 38, 37, 26, 15 };
      radixSort.sort(reverseSortedArray);
      System.out.print("Reverse Sorted Array Test: ");
      printArray(reverseSortedArray);

      // Test Case 4: Single Element Array
      int[] singleElementArray = { 42 };
      radixSort.sort(singleElementArray);
      System.out.print("Single Element Array Test: ");
      printArray(singleElementArray);

      // Test Case 5: Empty Array
      int[] emptyArray = {};
      radixSort.sort(emptyArray);
      System.out.print("Empty Array Test: ");
      printArray(emptyArray);

      // Test Case 6: Array with Duplicates
      int[] arrayWithDuplicates = { 4, 22, 4, 111, 22 };
      radixSort.sort(arrayWithDuplicates);
      System.out.print("Array with Duplicates Test: ");
      printArray(arrayWithDuplicates);

      // Test Case 7: Array with Negative Numbers
      int[] arrayWithNegatives = { -5, 3, -8, 1, 2 };
      radixSort.sort(arrayWithNegatives);
      System.out.print("Array with Negative Numbers Test: ");
      printArray(arrayWithNegatives);

      // Test Case 8: Large Random Array (Performance Test)
      int size = 10000; // You can change the array size
      int[] largeRandomArray = generateRandomArray(size);
      long startTime = System.nanoTime();
      radixSort.showSteps = false;
      radixSort.sort(largeRandomArray);
      long endTime = System.nanoTime();
      System.out.println(
            "Large Random Array Test: Sorted " + size + " elements in " + (endTime - startTime) / 1_000_000.0 + " ms");
      System.out.println("Is the large array sorted? " + isSorted(largeRandomArray));
   }

   // Helper method to print an array
   private static void printArray(int[] array) {
      for (int num : array) {
         System.out.print(num + " ");
      }
      System.out.println();
   }

   // Helper method to generate a random array
   private static int[] generateRandomArray(int size) {
      Random rand = new Random();
      int[] array = new int[size];
      for (int i = 0; i < size; i++) {
         array[i] = rand.nextInt(1000); // Generate random numbers between 0 and 999
      }
      return array;
   }

   // Helper method to check if an array is sorted
   private static boolean isSorted(int[] array) {
      for (int i = 0; i < array.length - 1; i++) {
         if (array[i] > array[i + 1]) {
            return false;
         }
      }
      return true;
   }
}
