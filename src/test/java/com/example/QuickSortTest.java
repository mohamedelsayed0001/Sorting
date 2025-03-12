package src.test.java.com.example;

import  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.com.example.QuickSort;

import java.util.Arrays;
import java.util.Random;

class QuickSortTest {
    private QuickSort quickSort;

    @BeforeEach
    void setUp() {
        quickSort = new QuickSort();
    }

    @Test
    void testSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        quickSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testUnsortedArray() {
        int[] array = {5, 3, 8, 1, 2};
        int[] expected = {1, 2, 3, 5, 8};
        quickSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testReverseSortedArray() {
        int[] array = {9, 8, 7, 6, 5};
        int[] expected = {5, 6, 7, 8, 9};
        quickSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSingleElementArray() {
        int[] array = {42};
        int[] expected = {42};
        quickSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        int[] expected = {};
        quickSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] array = {4, 2, 4, 1, 2};
        int[] expected = {1, 2, 2, 4, 4};
        quickSort.sort(array);
        assertArrayEquals(expected, array);
    }
    @Test
    void testQuickSort() {
        int size = 10000; // You can change the array size
        int[] array = generateRandomArray(size);

        QuickSort quickSort = new QuickSort();
        
        long startTime = System.nanoTime();
        quickSort.sort(array);
        long endTime = System.nanoTime();

        System.out.println("Execution Time for QuickSort (size " + size + "): " + (endTime - startTime) / 1_000_000.0 + " ms");

        assertTrue(isSorted(array), "Array should be sorted");
    }

    private int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(1000);
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
