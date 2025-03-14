package com.example;

import java.util.Scanner;

class Sort_Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Constructor constructor =new Constructor();
        
        int [] array = constructor.read_input();
        System.out.println("choose method :");
        System.out.println("1- O(n^2) ");
        System.out.println("2- O(n log n)");
        System.out.println("3- O(n)");
        int method = scanner.nextInt();



        if(method == 2){
            SortingStrategy sort = new QuickSort();
            long startTime = System.nanoTime();
            sort.sort(array);
            long endTime = System.nanoTime();
            System.out.println("Execution Time for QuickSort : " + (endTime - startTime) / 1_000_000.0 + " ms");
        }// } else if (method == 3){
        //     SortingStrategy sort = new RadixSort();
        //     long startTime = System.nanoTime();
        //     sort.sort(array);
        //     long endTime = System.nanoTime();
        //     System.out.println("Execution Time for RadixSort : " + (endTime - startTime) / 1_000_000.0 + " ms");
        // } 
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+", ");
        }

    }
    
}