package src.main.java.com.example;
import java.util.Scanner;
public class SortingUnit {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuickSort quickSort = new QuickSort();
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        long startTime = System.nanoTime();
        quickSort.sort(arr);
        long endTime = System.nanoTime();
        System.out.println("\nExecution Time for QuickSort: " + (endTime - startTime) / 1_000_000.0 + " ms");


        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        scanner.close();
    
    }
}