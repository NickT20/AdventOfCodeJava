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

    public int ExecutePart2(String file, int red, int blue, int green) throws IOException {
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
            var r = colorCounts.get("red");
            var b = colorCounts.get("blue");
            var g = colorCounts.get("green");
            result += r * b * g;
        }
        return result;
    }
}