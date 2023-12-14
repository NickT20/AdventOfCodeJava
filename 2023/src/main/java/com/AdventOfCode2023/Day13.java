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

    public long ExecutePart2(String file) throws IOException {
        var rows = new ArrayList<String>();
        var result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                if (line.isEmpty()) {
                    var firstResult = FindRow(rows);
                    var outcome = -1;
                    var count = 0;
                    while (outcome == -1) {
                        var rowLength = rows.get(0).length();
                        var row = count / rowLength;
                        var column = count % rowLength;
                        var r = rows.get(row);
                        var temp = r.charAt(column);
                        r = r.substring(0, column) + (temp == '#' ? '.' : '#') + r.substring(column + 1);
                        rows.set(row, r);

                        outcome = Calculate(rows, firstResult);

                        if (outcome == -1) {
                            r = r.substring(0, column) + temp + r.substring(column + 1);
                            rows.set(row, r);
                        }
                        count++;
                    }
                    result += outcome;
                    rows = new ArrayList<>();
                } else {
                    rows.add(line);
                }
            }
        }

        var outcome = -1;
        var count = 0;
        var firstResult = FindRow(rows);
        while (outcome == -1) {
            var rowLength = rows.get(0).length();
            var row = count / rowLength;
            var column = count % rowLength;
            var r = rows.get(row);
            var temp = r.charAt(column);
            r = r.substring(0, column) + (temp == '#' ? '.' : '#') + r.substring(column + 1);
            rows.set(row, r);

            outcome = Calculate(rows, firstResult);

            if (outcome == -1) {
                r = r.substring(0, column) + temp + r.substring(column + 1);
                rows.set(row, r);
            }
            count++;
        }

        result += outcome;
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
        var numberOfRows = FindMirror(rows);
        if (numberOfRows > -1) {
            return numberOfRows * 100;
        }
        var numberOfColumns = FindMirror(columns);
        if (numberOfColumns > -1) {
            return numberOfColumns;
        }
        return -1;
    }

    private int Calculate(ArrayList<String> rows, String firstRow) {
        var firstWasRow = false;
        if (firstRow.charAt(0) == 'R') {
            firstWasRow = true;
        }
        var rowNum = Integer.parseInt(firstRow.substring(1));
        var columns = new ArrayList<String>();
        for(var x = 0; x < rows.get(0).length(); x++) {
            var sb = new StringBuilder();
            for (var y = 0; y < rows.size(); y++) {
                sb.append(rows.get(y).charAt(x));
            }
            columns.add(sb.toString());
        }
        var numberOfRows = -1;
        if (firstWasRow) {
            numberOfRows = FindMirror(rows, rowNum);
        } else {
            numberOfRows = FindMirror(rows);
        }
        if (numberOfRows > -1) {
             return numberOfRows * 100;
        }
        var numberOfColumns =-1;

        if (!firstWasRow) {
            numberOfColumns = FindMirror(columns, rowNum);
        } else {
            numberOfColumns = FindMirror(columns);
        }
        if (numberOfColumns > -1) {
            return numberOfColumns;
        }
        return -1;
    }

    private String FindRow(ArrayList<String> rows) {
        var columns = new ArrayList<String>();
        for(var x = 0; x < rows.get(0).length(); x++) {
            var sb = new StringBuilder();
            for (var y = 0; y < rows.size(); y++) {
                sb.append(rows.get(y).charAt(x));
            }
            columns.add(sb.toString());
        }
        var numberOfRows = FindMirror(rows);
        if (numberOfRows > -1) {
            return "R" + numberOfRows;
        }
        var numberOfColumns = FindMirror(columns);
        if (numberOfColumns > -1) {
            return "C" + numberOfColumns;
        }
        return "";
    }

    private int FindMirror(ArrayList<String> rows, Integer skip) {
        for (var x = 1; x < rows.size(); x++) {
            if (skip == x) {
                continue;
            }
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

        return -1;
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

        return -1;
    }
}
