package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        var queue = new LinkedList<Character>();
        for (char turn: lines.get(0).toCharArray()) {
            queue.add(turn);
        }

        var nodes = new HashMap<String, Node>();
        for (var lineNumber = 2; lineNumber < lines.size(); lineNumber++) {
            var line = lines.get(lineNumber);
            var node = new Node();
            node.Name = line.substring(0, 3);
            node.Left = line.substring(7, 10);
            node.Right = line.substring(12, 15);
            nodes.put(node.Name, node);
        }

        var steps = 0;
        var found = false;
        var currentNode = nodes.get("AAA");
        while(!found) {
            var step = queue.pop();
            queue.add(step);
            currentNode = step == 'L' ? nodes.get(currentNode.Left) : nodes.get(currentNode.Right);
            steps++;
            if (currentNode.Name.equals("ZZZ")) { found = true; }
        }

        return steps;
    }

    public BigInteger ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        var queue = new LinkedList<Character>();
        for (char turn: lines.get(0).toCharArray()) {
            queue.add(turn);
        }

        var nodes = new HashMap<String, Node>();
        for (var lineNumber = 2; lineNumber < lines.size(); lineNumber++) {
            var line = lines.get(lineNumber);
            var node = new Node();
            node.Name = line.substring(0, 3);
            node.Left = line.substring(7, 10);
            node.Right = line.substring(12, 15);
            nodes.put(node.Name, node);
        }

        var steps = 0;
        var currentNodes = new ArrayList<Node>();

        for(var node: nodes.keySet()) {
            if (node.endsWith("A")) {
                currentNodes.add(nodes.get(node));
            }
        }

        var foundLocations = new HashMap<String, Integer>();

        while(foundLocations.size() != currentNodes.size()) {
            var step = queue.pop();
            queue.add(step);

            steps++;

            for(var i = 0; i < currentNodes.size(); i++) {
                var node = step == 'L' ? nodes.get(currentNodes.get(i).Left) : nodes.get(currentNodes.get(i).Right);
                if (node.Name.endsWith("Z") && !foundLocations.containsKey(node.Name)) {
                    foundLocations.put(node.Name, steps);
                }
                currentNodes.set(i, node);
            }
        }

        return findLCM(new ArrayList<Integer>(foundLocations.values()));
    }

    // Least common denominator provided by ChatGPT
    private static BigInteger findLCM(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }

    // Function to find the LCM of an array of BigIntegers
    public static BigInteger findLCM(ArrayList<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        BigInteger lcm = BigInteger.valueOf(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            lcm = findLCM(lcm, BigInteger.valueOf(numbers.get(i)));
        }

        return lcm;
    }

    public class Node {
        String Name;
        String Left;
        String Right;
    }
}
