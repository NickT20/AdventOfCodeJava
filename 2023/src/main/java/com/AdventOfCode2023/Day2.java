package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class Day2 {
    public int ExecutePart1(String file, int red, int blue, int green) throws IOException {
        var result = 0;
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        for (int x = 0; x < lines.size(); x++) {
            Map<String, Integer> colorCounts = new HashMap<>();
            var line = lines.get(x);
            var pattern = Pattern.compile("(\\d+)\\s(\\w+)");
            var matcher = pattern.matcher(line);
            while(matcher.find()) {
                var color = matcher.group(2);
                var count = Integer.parseInt(matcher.group(1));
                if (count > colorCounts.getOrDefault(color, 0)) {
                    colorCounts.put(color, count);
                }
            }
            if (colorCounts.getOrDefault("red", 0) <= red && colorCounts.getOrDefault("blue", 0) <= blue && colorCounts.getOrDefault("green", 0) <= green) {
                var gameIdPattern = Pattern.compile("Game (\\d+):");
                var gameIdMatcher = gameIdPattern.matcher(line);
                if (gameIdMatcher.find()){
                    result += Integer.parseInt(gameIdMatcher.group(1));
                }
            }
        }
        return result;
    }

    public int ExecutePart2(String file) throws IOException {
        InputStream inputStream = null;
        int result = 0;
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        for (int x = 0; x < lines.size(); x++) {
            // Traverse the string
            var stack = new Stack<Integer>();
            var line = LookForCharacters(lines.get(x));
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    stack.push(Character.getNumericValue(line.charAt(i)));
                }
            }
            result += stack.firstElement() * 10 + stack.lastElement();
        }
        return result;
    }

    private String LookForCharacters(String line) {
        var index = 100;
        String number = "";
        do {
            index = 100;
            number = "";
            var tempIndex = line.indexOf("one");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "one";
            }
            tempIndex = line.indexOf("two");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "two";
            }
            tempIndex = line.indexOf("three");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "three";
            }
            tempIndex = line.indexOf("four");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "four";
            }
            tempIndex = line.indexOf("five");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "five";
            }
            tempIndex = line.indexOf("six");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "six";
            }
            tempIndex = line.indexOf("seven");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "seven";
            }
            tempIndex = line.indexOf("eight");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "eight";
            }
            tempIndex = line.indexOf("nine");
            if (tempIndex > -1 && tempIndex < index ) {
                index = tempIndex;
                number = "nine";
            }

            if (index < 100) {
                line = ReplaceCharactersWithNumbers(line, index, number);
            }
        } while(index < 100);
        return line;
    }
    private String ReplaceCharactersWithNumbers(String line, Integer index, String number) {
        var num = "0";
        switch (number) {
            case "one":
                num = "1";
                break;
            case "two":
                num = "2";
                break;
            case "three":
                num = "3";
                break;
            case "four":
                num = "4";
                break;
            case "five":
                num = "5";
                break;
            case "six":
                num = "6";
                break;
            case "seven":
                num = "7";
                break;
            case "eight":
                num = "8";
                break;
            case "nine":
                num = "9";
                break;
            default:
                break;
        }

        StringBuilder buf = new StringBuilder(line);

        buf.replace(index, index + 1, num);

        return buf.toString();
    }
}