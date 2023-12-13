package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day13 {
    public long ExecutePart1(String file) throws IOException {
        var rows = new ArrayList<String>();
        var result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                if (line.isEmpty()) {
                    result += Calculate(rows);
                    rows = new ArrayList<>();
                } else {
                    rows.add(line);
                }
            }
        }
        result += Calculate(rows);
        return result;
    }

    private int Calculate(ArrayList<String> rows) {
        var columns = new ArrayList<String>();
        for(var x = 0; x < rows.get(0).length(); x++) {
            var sb = new StringBuilder();
            for (var y = 0; y < rows.size(); y++) {
                sb.append(rows.get(y).charAt(x));
            }
            columns.add(sb.toString());
        }
        var numberOfColumns = FindMirror(columns);
        var numberOfRows = FindMirror(rows);
        return (numberOfRows * 100) + numberOfColumns;
    }

    private int FindMirror(ArrayList<String> rows) {
        for (var x = 1; x < rows.size(); x++) {
            if (Objects.equals(rows.get(x), rows.get(x - 1))) {
                // potential match
                var match = true;
                // now check the other rows
                var top = x - 2;
                var bottom = x + 1;
                while (top >= 0 && bottom < rows.size()) {
                    if (!Objects.equals(rows.get(top), rows.get(bottom))) {
                        match = false;
                        break;
                    }
                    top--;
                    bottom++;
                }

                if (match) {
                    // we did it
                    return x;
                }
            }
        }

        return 0;
    }
}