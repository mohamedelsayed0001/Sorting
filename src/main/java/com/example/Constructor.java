package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Constructor {
   public int[] read_input(String filename) {
      ArrayList<Integer> array = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(new InputStreamReader(
              Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename))))) {
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
