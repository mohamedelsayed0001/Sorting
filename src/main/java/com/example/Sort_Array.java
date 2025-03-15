package com.example;

import java.util.Arrays;
import java.util.Scanner;

class Sort_Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Constructor constructor = new Constructor();

        System.out.print("Enter file : (e.g. filename.txt) ");
        String filename = scanner.nextLine();

        int[] array = constructor.read_input(filename);

        int length = array.length;

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

            if(length <= 1000) {
                System.out.print("Do you want to see the steps? (y/n) : ");
                char showStepsChoice = scanner.next().charAt(0);
                if (showStepsChoice == 'y') {
                    showSteps = true;
                }
                System.out.println();
            }

            SortingStrategy sorter;
            long startTime;
            long endTime;
            String[] steps;

            switch (choice) {
                case 1:
                    sorter = new InsertionSort(); 
                    break;
                case 2:
                    sorter = new QuickSort();
                    break;
                case 3:
                    sorter = new RadixSort();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }
            startTime = System.nanoTime();
            steps = sorter.sort(array);
            endTime = System.nanoTime();
            System.out.println(
                    "Execution Time : " + (endTime - startTime) / 1_000_000.0 + " ms");

            if(showSteps) {
                for (String step : steps) {
                    System.out.println(step);
                }
            }

            System.out.println("Sorted Array: " + Arrays.toString(array));
        }
    }
}