package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Sort_Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file : (e.g. filename.txt) ");
        String filename = scanner.nextLine();

        int[] array = read_input(filename);

        while (true) {

            System.out.println("\nChoose method :");
            System.out.println("1- O(n^2) Insertion Sort");
            System.out.println("2- O(n log (n)) Quick Sort");
            System.out.println("3- O(n) Radix Sort");
            System.out.println("4- Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Exiting...");
                return;
            }

            boolean showSteps = false;
            System.out.print("Do you want to see the steps? (y/n) : ");
            char showStepsChoice = scanner.next().charAt(0);
            if (showStepsChoice == 'y') {
                showSteps = true;
            }
            System.out.println();

            SortingStrategy sorter;
            long startTime;
            long endTime;
            String[] steps;

            switch (choice) {
                case 1:
                    sorter = new InsertionSort();
                    startTime = System.nanoTime();
                    steps = sorter.sort(array);
                    endTime = System.nanoTime();
                    System.out.println(
                            "Execution Time for Insertion Sort : " + (endTime - startTime) / 1_000_000.0 + " ms");
                    break;
                case 2:
                    sorter = new QuickSort();
                    startTime = System.nanoTime();
                    steps = sorter.sort(array);
                    endTime = System.nanoTime();
                    System.out
                            .println("Execution Time for Quick Sort : " + (endTime - startTime) / 1_000_000.0 + " ms");
                    break;
                case 3:
                    sorter = new RadixSort();
                    startTime = System.nanoTime();
                    steps = sorter.sort(array);
                    endTime = System.nanoTime();
                    System.out
                            .println("Execution Time for Radix Sort : " + (endTime - startTime) / 1_000_000.0 + " ms");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }

            if(showSteps) {
                for (String step : steps) {
                    System.out.println(step);
                }
            }

            System.out.println("Sorted Array: " + Arrays.toString(array));
        }
    }

    private static int[] read_input(String filename) {
        ArrayList<Integer> array = new ArrayList<>();
        String path = "src/main/java/com/example/";
        path += filename;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                for (String value : values) {
                    array.add(Integer.parseInt(value.trim()));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new int[0];
        }
        int[] result = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i);
        }
        return result;
    }
}