package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day11 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        var nodes = new HashSet<Node>();
        var shiftUpCount = 0;
        // ROWS
        for(var y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            var found = false;
            for(var x = 0; x < line.length(); x++) {
                var c = line.charAt(x);
                if (c == '#') {
                    nodes.add(new Node(x, y + shiftUpCount, true));
                    found = true;
                } else {
                    nodes.add(new Node(x, y + shiftUpCount , false));
                }
            }
            if (!found) {
                // insert empty row
                shiftUpCount++;
                for(var x = 0; x < line.length(); x++) {
                    nodes.add(new Node(x, y + shiftUpCount, false));
                }
            }
        }

        // COLUMNS
        var lineLength = lines.get(0).length();
        var height = nodes.size() / lineLength;
        for(var x = 0; x < lineLength; x++) {
//            for (var y = 0; y < height - 1; y++) { // Don't care about the last row expanding
                int finalX = x;
                var count = nodes.stream().filter(n -> n.X == finalX && n.Galaxy).count();
                if (count == 0) {
                    // we have an empty y line
                    for (var y2 = 0; y2 < height; y2++) {
                        nodes.add(new Node(lineLength, y2, false));
                    }
                    lineLength++;
                    x++;
                    ShiftRight(nodes, x, height);
                }
//            }
        }

        var galaxies = nodes.stream().filter(n -> n.Galaxy).toList();

        var result = 0;
        for (var g = 0; g < galaxies.size(); g++) {
            var galaxy1 = galaxies.get(g);
            for (var g2 = 0; g2 < galaxies.size(); g2++) {
                var galaxy2 = galaxies.get(g2);
                var distance = CalculateManhattanDistance(galaxy1.X, galaxy1.Y, galaxy2.X, galaxy2.Y);
                result += distance;
            }
        }

        return result / 2; // Because I'm calculating 1 -> 9 and 9 -> 1 for example
    }

    private void Print(int lineLength, int height, HashSet<Node> nodes) {
        for (var y = 0; y < height; y++) {
            for(var x = 0; x < lineLength; x++) {
                int finalX = x;
                int finalY = y;
                var test = nodes.stream().filter(n2 -> n2.X == 0).toList();
                var n = nodes.stream().filter(n2 -> n2.X == finalX && n2.Y == finalY).findFirst().get().Galaxy;
                if (n) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int CalculateManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    public void ShiftRight(HashSet<Node> nodes, int x, int height) {
        var lineLength = nodes.size() / height;
//        for(var y = 0; y < height; y++) {
            for (var i = lineLength - 2; i >= x; i--) { // Don't care about the last row expanding
                int finalX = i;
                var nodesToShifts = nodes.stream().filter(node -> node.X == finalX && node.Galaxy).toList();
                if (nodesToShifts.size() > 0) {
                    for (var z = 0; z < nodesToShifts.size(); z++) {
                        var abc = nodesToShifts.get(z);
                        nodes.stream().filter(n -> n.X == abc.X + 1 && n.Y == abc.Y).findFirst().get().Galaxy = true;
                        abc.Galaxy = false;
                    }
                }
//            }
        }
    }

    public class Node {
        public int X;
        public int Y;
        public boolean Galaxy;
        public Node(int x, int y, boolean galaxy) {
            X = x;
            Y = y;
            Galaxy = galaxy;
        }
    }

}
