package com.example;

import java.util.Arrays;

public class RadixSort implements SortingStrategy {

   // Utility function to get maximum absolute value in arr[]
   private int getMax(int arr[], int n) {
      int mx = Math.abs(arr[0]);
      for (int i = 1; i < n; i++)
         if (Math.abs(arr[i]) > mx)
            mx = Math.abs(arr[i]);
      return mx;
   }

   // Counting Sort as a subroutine for Radix Sort
   private void countSort(int arr[], int n, int exp, boolean showSteps) {
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

      if (showSteps) {
         System.out.print("After sorting for exp = " + exp + ": " + Arrays.toString(arr));
         System.out.println();
      }
   }

   @Override
   public void sort(int arr[], boolean showSteps) {
      int n = arr.length;
      if (n == 0)
         return;

      // Separate negatives and positives
      int[] negatives = Arrays.stream(arr).filter(x -> x < 0).toArray();
      int[] positives = Arrays.stream(arr).filter(x -> x >= 0).toArray();

      // Sort negatives based on absolute values
      int maxNeg = negatives.length > 0 ? getMax(negatives, negatives.length) : 0;
      for (int exp = 1; maxNeg / exp > 0; exp *= 10)
         countSort(negatives, negatives.length, exp, showSteps);

      // Sort positives normally
      int maxPos = positives.length > 0 ? getMax(positives, positives.length) : 0;
      for (int exp = 1; maxPos / exp > 0; exp *= 10)
         countSort(positives, positives.length, exp, showSteps);

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
}