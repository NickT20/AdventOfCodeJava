package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public int ExecutePart2(String file) throws IOException {
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
        var currentNodes = new ArrayList<Node>();

        for(var node: nodes.keySet()) {
            if (node.endsWith("A")) {
                currentNodes.add(nodes.get(node));
            }
        }

        while(!found) {
            var step = queue.pop();
            queue.add(step);
            found = true;

            for(var i = 0; i < currentNodes.size(); i++) {
                var node = step == 'L' ? nodes.get(currentNodes.get(i).Left) : nodes.get(currentNodes.get(i).Right);
                if (found && !node.Name.endsWith("Z")) {
                    found = false;
                }
                currentNodes.set(i, node);
            }
            steps++;
        }

        return steps;
    }

    public class Node {
        String Name;
        String Left;
        String Right;
    }
}
