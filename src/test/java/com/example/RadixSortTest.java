package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

class RadixSortTest {
   private RadixSort radixSort;

   @BeforeEach
   void setUp() {
      radixSort = new RadixSort();
   }

   @Test
   void testSortedArray() {
      int[] array = { 1, 2, 3, 4, 5 };
      int[] expected = { 1, 2, 3, 4, 5 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testUnsortedArray() {
      int[] array = { 59, 53, 48, 81, 92 };
      int[] expected = { 48, 53, 59, 81, 92 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testReverseSortedArray() {
      int[] array = { 129, 38, 37, 26, 15 };
      int[] expected = { 15, 26, 37, 38, 129 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testSingleElementArray() {
      int[] array = { 42 };
      int[] expected = { 42 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testEmptyArray() {
      int[] array = {};
      int[] expected = {};
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testArrayWithDuplicates() {
      int[] array = { 4, 22, 4, 111, 22 };
      int[] expected = { 4, 4, 22, 22, 111 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testArrayWithNegativeNumbers() {
      int[] array = { -5, 3, -8, 1, 2 };
      int[] expected = { -8, -5, 1, 2, 3 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testArrayWithOnlyNegativeNumbers() {
      int[] array = { -5, -3, -89, -153, -22 };
      int[] expected = { -153, -89, -22, -5, -3 };
      radixSort.sort(array, false);
      assertArrayEquals(expected, array);
   }

   @Test
   void testLargeRandomArray() {
      int size = 10000; // You can change the array size
      int[] array = generateRandomArray(size);

      long startTime = System.nanoTime();
      radixSort.sort(array, false);
      long endTime = System.nanoTime();

      System.out.println(
            "Execution Time for RadixSort (size " + size + "): " + (endTime - startTime) / 1_000_000.0 + " ms");

      assertTrue(isSorted(array), "Array should be sorted");
   }

   private int[] generateRandomArray(int size) {
      Random rand = new Random();
      int[] array = new int[size];
      for (int i = 0; i < size; i++) {
         array[i] = rand.nextInt(1000); // Generate random numbers between 0 and 999
      }
      return array;
   }

   private boolean isSorted(int[] array) {
      for (int i = 0; i < array.length - 1; i++) {
         if (array[i] > array[i + 1]) {
            return false;
         }
      }
      return true;
   }
}