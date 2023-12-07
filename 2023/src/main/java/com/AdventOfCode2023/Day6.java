package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        var times = new ArrayList<Integer>();
        var distances = new ArrayList<Integer>();
        String pattern = "[\\d]+";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(lines.get(0));

        while (matcher.find()) {
            times.add(Integer.parseInt(matcher.group()));
        }

        matcher = regex.matcher(lines.get(1));
        while (matcher.find()) {
            distances.add(Integer.parseInt(matcher.group()));
        }

        var counts = new ArrayList<Integer>();
        for (var x = 0; x < times.size(); x++) {
            var time = times.get(x);
            var distance = distances.get(x);
            var count = 0;
            for (var i = 1; i < time; i++) {
                var result = (time - i) * i;
                if (result > distance) {
                    count++;
                }
            }
            counts.add(count);
        }

        return multiplyArrayListValues(counts);
    }

    public BigInteger ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        BigInteger time = BigInteger.ZERO;
        BigInteger distance = BigInteger.ZERO;
        String pattern = "[\\d]+";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(lines.get(0));

        while (matcher.find()) {
            time = BigInteger.valueOf(Long.parseLong(matcher.group()));
        }

        matcher = regex.matcher(lines.get(1));
        while (matcher.find()) {
            distance = BigInteger.valueOf(Long.parseLong(matcher.group()));
        }

        var b = true;
        var startingNumber = time.divide(BigInteger.valueOf(2));
        while (b) {
            var result = time.subtract(startingNumber).multiply(startingNumber);
            if (distance.compareTo(result) > 0) {
                b = false;
                continue;
            }
            startingNumber = startingNumber.subtract(BigInteger.ONE);
        }
        var first = startingNumber;
        b = true;
        startingNumber = time.divide(BigInteger.valueOf(2));
        while (b) {
            var result = time.subtract(startingNumber).multiply(startingNumber);
            if (distance.compareTo(result) > 0) {
                b = false;
                continue;
            }
            startingNumber = startingNumber.add(BigInteger.ONE);
        }

        return startingNumber.subtract(first).subtract(BigInteger.ONE);
    }

    private static int multiplyArrayListValues(ArrayList<Integer> integerList) {
        // Initialize the product to 1
        int product = 1;

        // Multiply each value in the list
        for (int value : integerList) {
            product *= value;
        }

        return product;
    }
}
