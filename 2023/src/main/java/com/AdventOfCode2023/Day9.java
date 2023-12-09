package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day9 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        int results = 0;
        for(var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var parts = line.split(" ");
            var numbers = new int[parts.length];
            for(var x = 0; x < parts.length; x++) {
                numbers[x] = Integer.parseInt(parts[x]);
            }
            results += FindValue(numbers) + numbers[parts.length - 1];
        }

        return results;
    }

    public int ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        int results = 0;
        for(var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var parts = line.split(" ");
            var numbers = new int[parts.length];
            for(var x = 0; x < parts.length; x++) {
                numbers[x] = Integer.parseInt(parts[x]);
            }
            ReverseArray(numbers);
            results += FindValue(numbers) + numbers[parts.length - 1];
        }

        return results;
    }

    public int FindValue(int[] numbers) {
        var newNumbers = new int[numbers.length - 1];
        for(var x = 1; x < numbers.length; x++) {
            newNumbers[x-1] = numbers[x] - numbers[x-1];
        }

        if(AllZeros(newNumbers)) {
            return 0;
        }

        return FindValue(newNumbers) + newNumbers[newNumbers.length - 1];
    }

    private static boolean AllZeros(int[] array) {
        for (int value : array) {
            if (value != 0) {
                return false; // If any element is not zero, return false
            }
        }
        return true; // All elements are zero
    }

    private static void ReverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            // Swap elements at start and end indices
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            // Move indices towards the center
            start++;
            end--;
        }
    }
}
