package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

class ALLSortTest {
    private QuickSort quickSort;
    private RadixSort radixSort;
    private InsertionSort insertionSort;
    private static List<Long> quickSortTimes = new ArrayList<>();
    private static List<Long> radixSortTimes = new ArrayList<>();
    private static List<Long> insertionSortTimes = new ArrayList<>();
    private static List<Integer> sizes = Arrays.asList(10, 50, 100, 500, 1000, 5000, 10000, 20000, 50000, 100000);
    
    @BeforeEach
    void setUp() {
        quickSort = new QuickSort();
        radixSort = new RadixSort();
        insertionSort = new InsertionSort();
    }

    private int[] generateRandomArray(int size) {
        Random random = new Random();
        return random.ints(size, 0, 10000).toArray();
    }

    private long measureSortTime(Runnable sortFunction) {
        long startTime = System.nanoTime();
        sortFunction.run();
        return System.nanoTime() - startTime;
    }

    @Test
    void testSorts() {
        for (int size : sizes) {
            int[] array = generateRandomArray(size);
            int[] expected = Arrays.copyOf(array, array.length);
            Arrays.sort(expected);

            int[] quickArray = Arrays.copyOf(array, array.length);
            int[] radixArray = Arrays.copyOf(array, array.length);
            int[] insertionArray = Arrays.copyOf(array, array.length);

            quickSortTimes.add(measureSortTime(() -> quickSort.sort(quickArray)));
            radixSortTimes.add(measureSortTime(() -> radixSort.sort(radixArray)));
            insertionSortTimes.add(measureSortTime(() -> insertionSort.sort(insertionArray)));

            assertArrayEquals(expected, quickArray, "QuickSort failed");
            assertArrayEquals(expected, radixArray, "RadixSort failed");
            assertArrayEquals(expected, insertionArray, "InsertionSort failed");
        }
    }

    @AfterAll
    static void plotResults() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < sizes.size(); i++) {
            dataset.addValue(quickSortTimes.get(i), "QuickSort", sizes.get(i));
            dataset.addValue(radixSortTimes.get(i), "RadixSort", sizes.get(i));
            dataset.addValue(insertionSortTimes.get(i), "InsertionSort", sizes.get(i));
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
            "Sorting Algorithms Execution Time",
            "Array Size",
            "Time (ns)",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

        JFrame frame = new JFrame("Sorting Performance Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(lineChart));
        frame.pack();
        frame.setVisible(true);
        
        
    }
}
