package com.example;

import java.util.*;

public class RadixSort implements SortingStrategy {
   @Override
   public String getName() {
      return "Radix Sort";
   }

   @Override
   public String getComplexity() {
      return "O(n)";
   }

   @Override
   public String[] sort(int arr[]) {
      int n = arr.length;
      if (n == 0)
         return new String[0];

      List<String> steps = new ArrayList<>();
      int[] negatives = Arrays.stream(arr).filter(x -> x < 0).toArray();
      int[] positives = Arrays.stream(arr).filter(x -> x >= 0).toArray();

      int maxNeg = negatives.length > 0 ? getMax(negatives, negatives.length) : 0;
      for (int exp = 1; maxNeg / exp > 0; exp *= 10)
         countSort(negatives, negatives.length, exp, steps);

      int maxPos = positives.length > 0 ? getMax(positives, positives.length) : 0;
      for (int exp = 1; maxPos / exp > 0; exp *= 10)
         countSort(positives, positives.length, exp, steps);

      for (int i = 0, j = negatives.length - 1; i < j; i++, j--) {
         int temp = negatives[i];
         negatives[i] = negatives[j];
         negatives[j] = temp;
      }

      int index = 0;
      for (int num : negatives)
         arr[index++] = num;
      for (int num : positives)
         arr[index++] = num;

      return steps.toArray(new String[0]);
   }

   private void countSort(int arr[], int n, int exp, List<String> steps) {
      int output[] = new int[n];
      int count[] = new int[10];
      Arrays.fill(count, 0);

      for (int i = 0; i < n; i++)
         count[(Math.abs(arr[i]) / exp) % 10]++;

      for (int i = 1; i < 10; i++)
         count[i] += count[i - 1];

      for (int i = n - 1; i >= 0; i--) {
         output[count[(Math.abs(arr[i]) / exp) % 10] - 1] = arr[i];
         count[(Math.abs(arr[i]) / exp) % 10]--;
      }

      for (int i = 0; i < n; i++)
         arr[i] = output[i];

      steps.add("After sorting for exp = " + exp + ": " + Arrays.toString(arr));
   }

   private int getMax(int arr[], int n) {
      int mx = Math.abs(arr[0]);
      for (int i = 1; i < n; i++)
         if (Math.abs(arr[i]) > mx)
            mx = Math.abs(arr[i]);
      return mx;
   }

}
