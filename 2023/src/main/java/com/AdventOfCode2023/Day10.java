package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day10 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        String start = "";
        var map = new HashMap<String, Character>();
        for(var y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            for(var x = 0; x < line.length(); x++) {
                var c = line.charAt(x);
                map.put(MessageFormat.format("{0}-{1}", y, x), c);

                if (c == 'S') {
                    start = MessageFormat.format("{0}-{1}", y, x);
                }
            }
        }

        // y is inverted

        var previousMove = GetDirectionToStart(map, start);
        var y = Integer.parseInt(start.split("-")[0]);
        var x = Integer.parseInt(start.split("-")[1]);
        switch (previousMove) {
            case "up":
                start = MessageFormat.format("{0}-{1}", y - 1, x);
                break;
            case "down":
                start = MessageFormat.format("{0}-{1}", y + 1, x);
                break;
            case "left":
                start = MessageFormat.format("{0}-{1}", y, x - 1);
                break;
            default:
                start = MessageFormat.format("{0}-{1}", y, x + 1);
                break;
        }

        var instruction = map.get(start);
        var result = 1;
        // Recursion sucked. Trying while loop
        do {
            y = Integer.parseInt(start.split("-")[0]);
            x = Integer.parseInt(start.split("-")[1]);
            switch (instruction) {
                case '|':
                    if (previousMove == "up") {
                        start = MessageFormat.format("{0}-{1}", y - 1, x);
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y + 1, x);
                    previousMove = "down";
                    break;
                case '-':
                    if (previousMove == "right") {
                        start = MessageFormat.format("{0}-{1}", y, x + 1);
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x - 1);
                    previousMove = "left";
                    break;
                case 'F':
                    if (previousMove == "left") {
                        start = MessageFormat.format("{0}-{1}", y + 1, x);
                        previousMove = "down";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x + 1);
                    previousMove = "right";
                    break;
                case 'L':
                    if (previousMove == "left") {
                        start = MessageFormat.format("{0}-{1}", y - 1, x);
                        previousMove = "up";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x + 1);
                    previousMove = "right";
                    break;
                case 'J':
                    if (previousMove == "right") {
                        start = MessageFormat.format("{0}-{1}", y - 1, x);
                        previousMove = "up";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x - 1);
                    previousMove = "left";
                    break;
                default: // 7
                    if (previousMove == "right") {
                        start = MessageFormat.format("{0}-{1}", y + 1, x);
                        previousMove = "down";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x - 1);
                    previousMove = "left";
                    break;
            }
            instruction = map.get(start);
            result++;
        } while (instruction != 'S') ;
        return result / 2;
    }

    public int ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        String start = "";
        var map = new HashMap<String, Character>();
        var visitedTiles = new HashSet<String>();
        var visitedX = new ArrayList<Integer>();
        var visitedY = new ArrayList<Integer>();
        for(var y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            for(var x = 0; x < line.length(); x++) {
                var c = line.charAt(x);
                map.put(MessageFormat.format("{0}-{1}", y, x), c);

                if (c == 'S') {
                    start = MessageFormat.format("{0}-{1}", y, x);
                }
            }
        }

        // y is inverted

        var previousMove = GetDirectionToStart(map, start);
        var y = Integer.parseInt(start.split("-")[0]);
        var x = Integer.parseInt(start.split("-")[1]);
        switch (previousMove) {
            case "up":
                start = MessageFormat.format("{0}-{1}", y - 1, x);
                visitedY.add(y-1);
                visitedX.add(x);
                break;
            case "down":
                start = MessageFormat.format("{0}-{1}", y + 1, x);
                visitedY.add(y+1);
                visitedX.add(x);
                break;
            case "left":
                start = MessageFormat.format("{0}-{1}", y, x - 1);
                visitedY.add(y);
                visitedX.add(x-1);
                break;
            default:
                start = MessageFormat.format("{0}-{1}", y, x + 1);
                visitedY.add(y);
                visitedX.add(x+1);
                break;
        }

        visitedTiles.add(start);
        var instruction = map.get(start);
        // Recursion sucked. Trying while loop
        do {
            y = Integer.parseInt(start.split("-")[0]);
            x = Integer.parseInt(start.split("-")[1]);
            switch (instruction) {
                case '|':
                    if (previousMove == "up") {
                        start = MessageFormat.format("{0}-{1}", y - 1, x);
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y + 1, x);
                    previousMove = "down";
                    break;
                case '-':
                    if (previousMove == "right") {
                        start = MessageFormat.format("{0}-{1}", y, x + 1);
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x - 1);
                    previousMove = "left";
                    break;
                case 'F':
                    if (previousMove == "left") {
                        start = MessageFormat.format("{0}-{1}", y + 1, x);
                        previousMove = "down";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x + 1);
                    previousMove = "right";
                    break;
                case 'L':
                    if (previousMove == "left") {
                        start = MessageFormat.format("{0}-{1}", y - 1, x);
                        previousMove = "up";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x + 1);
                    previousMove = "right";
                    break;
                case 'J':
                    if (previousMove == "right") {
                        start = MessageFormat.format("{0}-{1}", y - 1, x);
                        previousMove = "up";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x - 1);
                    previousMove = "left";
                    break;
                default: // 7
                    if (previousMove == "right") {
                        start = MessageFormat.format("{0}-{1}", y + 1, x);
                        previousMove = "down";
                        break;
                    }
                    start = MessageFormat.format("{0}-{1}", y, x - 1);
                    previousMove = "left";
                    break;
            }
            instruction = map.get(start);
            visitedY.add(y);
            visitedX.add(x);
            visitedTiles.add(start);
        } while (instruction != 'S') ;
        var result = 0;

        Integer[] xs = new Integer[visitedX.size()];
        xs = visitedX.toArray(xs);
        Integer[] ys = new Integer[visitedY.size()];
        ys = visitedY.toArray(ys);
        for(var y2 = 0; y2 < lines.size(); y2++) {
            for(var x2 = 0; x2 < lines.get(0).length(); x2++) {
                if (visitedTiles.contains(MessageFormat.format("{0}-{1}", y2, x2))) {
                    System.out.print('x');
                } else if(isPointInPolygon(xs, ys, x2, y2)) {
                    result++;
                    System.out.print('.');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }

        return result;
    }

    private String GetDirectionToStart(HashMap<String, Character> map, String coordinates) {
        var y = Integer.parseInt(coordinates.split("-")[0]);
        var x = Integer.parseInt(coordinates.split("-")[1]);
        if (map.get(MessageFormat.format("{0}-{1}", y + 1, x)) == '|' || map.get(MessageFormat.format("{0}-{1}", y + 1, x)) == 'F') {
            return "down";
        }
        if (map.get(MessageFormat.format("{0}-{1}", y - 1, x)) == '|' || map.get(MessageFormat.format("{0}-{1}", y - 1, x)) == 'L') {
            return "up";
        }
        if (map.get(MessageFormat.format("{0}-{1}", y, x + 1)) == '-' || map.get(MessageFormat.format("{0}-{1}", y, x + 1)) == 'J') {
            return "right";
        }
        return "left";
    }

    public static boolean isPointInPolygon(Integer[] polygonX, Integer[] polygonY, int x, int y) {
        int n = polygonX.length;
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            if ((polygonY[i] > y) != (polygonY[j] > y) &&
                    (x < (polygonX[j] - polygonX[i]) * (y - polygonY[i]) / (polygonY[j] - polygonY[i]) + polygonX[i])) {
                inside = !inside;
            }
        }

        return inside;
    }
}
