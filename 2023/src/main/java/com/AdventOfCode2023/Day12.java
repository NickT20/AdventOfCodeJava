package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 {
    private HashMap<String, Long> cache = new HashMap<>();
    public long ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        long results = 0;
        for(var line : lines) {
            var parts = line.split(" ");
            var groupNumbers = parts[1].split(",");
            var groups = Arrays.stream(groupNumbers).map(g -> Integer.valueOf(g)).toList();
            results += Count(parts[0], groups);
        }

        return results; // Because I'm calculating 1 -> 9 and 9 -> 1 for example
    }

    public long ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        long results = 0;
        for(var line : lines) {
            var parts = line.split(" ");
            StringBuilder puzzle = new StringBuilder(parts[0]);
            StringBuilder groupsString = new StringBuilder(parts[1]);

            for (var i = 0; i < 4; i++) {
                puzzle.append("?").append(parts[0]);
                groupsString.append(",").append(parts[1]);
            }

            var groupNumbers = groupsString.toString().split(",");
            var groups = Arrays.stream(groupNumbers).map(g -> Integer.valueOf(g)).toList();

            results += Count(puzzle.toString(), groups);
        }

        return results; // Because I'm calculating 1 -> 9 and 9 -> 1 for example
    }

    private long Count(String puzzle, List<Integer> groups) {
        var commaSeparatedString = String.join(", ", groups.stream()
                .map(Object::toString)
                .collect(Collectors.toList()));
        var key = puzzle + "-" + commaSeparatedString;
        if (cache.containsKey(key)) return cache.get(key);

        if (puzzle.isBlank()) {
            return groups.isEmpty() ? 1 : 0;
        }
        long result = 0;
        var character = puzzle.charAt(0);
        if (character == '.') {
            result = Count(puzzle.substring(1), groups);
        } else if (character == '?') {
            result = Count("." + puzzle.substring(1), groups);
            result += Count("#" + puzzle.substring(1), groups);
        } else {
            if (groups.isEmpty()) {
                return 0;
            }
            var group = groups.get(0);
            if (group <= puzzle.length() && puzzle.chars().limit(group).allMatch(c -> c == '?' || c == '#')) {
                var newGroups = groups.subList(1, groups.size());
                if (group == puzzle.length()) {
                    return newGroups.isEmpty() ? 1 : 0;
                } else if (puzzle.charAt(group) == '.') {
                    result = Count(puzzle.substring(group + 1), newGroups);
                } else if (puzzle.charAt(group) == '?') {
                    // has to be operational
                    result = Count('.' + puzzle.substring(group + 1), newGroups);
                }
            }
        }
        cache.put(key, result);
        return result;
    }
}
