package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Constructor {
   public int[] read_input(String filename) {
      ArrayList<Integer> array = new ArrayList<>();
      String path = "C:\\Users\\DELL\\OneDrive\\Desktop\\2nd term\\DSA\\labs\\lab1\\src\\main\\java\\com\\example\\input.txt";
      //path += filename;
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
