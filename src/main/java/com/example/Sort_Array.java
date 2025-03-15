package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Sort_Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<SortingStrategy> sortingStrategies = List.of(new InsertionSort(), new QuickSort(), new RadixSort());

        Constructor constructor = new Constructor();
        System.out.print("Enter file : (e.g. filename.txt) ");
        String filename = scanner.nextLine();
        int[] array = constructor.read_input(filename);

        int length = array.length;

        while (true) {
            System.out.println("\nChoose method :");
            for (int i = 0; i < sortingStrategies.size(); i++) {
                System.out.printf("%d- %-20s %s%n", i + 1, sortingStrategies.get(i).getName(), sortingStrategies.get(i).getComplexity());
            }
            System.out.println(sortingStrategies.size()+1 + "- Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            if (choice == sortingStrategies.size()+1) {
                System.out.println("Exiting...");
                return;
            }
            if(choice < 1 || choice > sortingStrategies.size()+1){
                System.out.println("Invalid choice. Try again.");
                continue;
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


            SortingStrategy sorter = sortingStrategies.get(choice-1) ;
            long startTime = System.nanoTime();
            String[] steps = sorter.sort(array);
            long endTime = System.nanoTime();
            System.out.println(
                    "Execution Time for Insertion Sort : " + (endTime - startTime) / 1_000_000.0 + " ms");

            if(showSteps) {
                for (String step : steps) {
                    System.out.println(step);
                }
            }

            System.out.println("Sorted Array: " + Arrays.toString(array));
        }
    }
}