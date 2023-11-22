package com.example.adventofcode;

public class Day1 {
    public int ExecutePart1(String input) {
        int count = 0;
        for (int i = 0; i < input.length() - 1; i++){
            if (input.charAt(i) == input.charAt(i+1)) {
                count += input.charAt(i) - '0';
            }
        }

        if (input.charAt(0) == input.charAt(input.length() - 1)) {
            count += input.charAt(0) - '0';
        }

        return count;
    }
    public int ExecutePart2(String input) {
        var count = 0;
        var length = input.length();
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == input.charAt(GetHalfwayIndex(i, length))) {
                count += input.charAt(i) - '0';
            }
        }

//        if (input.charAt(0) == input.charAt(input.length() - 1)) {
//            count += input.charAt(0) - '0';
//        }

        return count;
    }

    private int GetHalfwayIndex(int current, int length) {
        var half = length / 2;
        var result = current + half;
        if (result >= length) {
            return result - length;
        }
        return result;
    }
}
