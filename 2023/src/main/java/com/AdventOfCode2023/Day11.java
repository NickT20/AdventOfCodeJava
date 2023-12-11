package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
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

    public BigInteger ExecutePart2(String file, int expansion) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        // Let's rethink this.
        // Only store the galaxies.
        var nodes = new HashSet<Node>();
        var shiftUpCount = 0;
        for(var y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            for(var x = 0; x < line.length(); x++) {
                var c = line.charAt(x);
                if (c == '#') {
                    nodes.add(new Node(x, y + shiftUpCount));
                }
            }
        }

        var emptyRows = new ArrayList<Integer>();
        var emptyColumns = new ArrayList<Integer>();

        for (var x = 0; x < lines.get(0).length(); x++) {
            int finalX = x;
            var count = nodes.stream().filter(n -> n.X == finalX).count();
            if (count == 0) {
                emptyColumns.add(x);
            }
        }

        var height = nodes.stream()
                .map(Node::getY)
                .max(Integer::compare);
        for (var y = 0; y < height.get(); y++) {
            int finalY = y;
            var count = nodes.stream().filter(n -> n.Y == finalY).count();
            if (count == 0) {
                emptyRows.add(y);
            }
        }

        var shift = 0;
        for (var y: emptyRows) {
            var finalShift = shift;
            var nodesToShift = nodes.stream().filter(n -> n.Y > (y + finalShift)).toList();
            for (var i = 0; i < nodesToShift.size(); i++) {
                nodesToShift.get(i).Y += expansion;
            }
            shift += expansion;
        }

        shift = 0;
        for (var x: emptyColumns) {
            var finalShift = shift;
            var nodesToShift = nodes.stream().filter(n -> n.X > (x + finalShift)).toList();
            for (var i = 0; i < nodesToShift.size(); i++) {
                nodesToShift.get(i).X += expansion;
            }
            shift += expansion;
        }

        var result = BigInteger.ZERO;
        var nodesArray = nodes.toArray();
        for (var g = 0; g < nodes.size(); g++) {
            var galaxy1 = (Node) nodesArray[g];
            for (var g2 = 0; g2 < nodes.size(); g2++) {
                var galaxy2 = (Node) nodesArray[g2];
                var distance = CalculateManhattanDistance(galaxy1.X, galaxy1.Y, galaxy2.X, galaxy2.Y);
                result = result.add(BigInteger.valueOf(distance));
            }
        }
//        Print(50, 50, nodes);
        return result.divide(BigInteger.TWO); // Because I'm calculating 1 -> 9 and 9 -> 1 for example
    }
    private void Print(int lineLength, int height, HashSet<Node> nodes) {
        for (var y = 0; y < height; y++) {
            for(var x = 0; x < lineLength; x++) {
                int finalX = x;
                int finalY = y;
                var n = nodes.stream().filter(n2 -> n2.X == finalX && n2.Y == finalY).findFirst();
                if (!n.isEmpty()) {
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
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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
        // Used for part 2
        public Node(int x, int y) {
            X = x;
            Y = y;
        }

        public Integer getY() {
            return Integer.valueOf(Y);
        }
    }

}
