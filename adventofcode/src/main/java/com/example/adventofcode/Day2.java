package com.example.adventofcode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public int ExecutePart1(String file) throws IOException {
        InputStream inputStream = null;
        var result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                var line = br.readLine();
                var parts = Arrays.stream(line.split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());
                result += parts.getLast() - parts.getFirst();
            }
        }
        return result;
    }

    public int ExecutePart2(String file) throws IOException {
        InputStream inputStream = null;
        var result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                var line = br.readLine();
                var parts = Arrays.stream(line.split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());
                result += FindResult(parts);
            }
        }
        return result;
    }

    private int FindResult(List<Integer> parts) {
        for(var i = 0; i < parts.size(); i++) {
            for(var x = i+1; x < parts.size(); x++) {
                if (parts.get(x) % parts.get(i) == 0) {
                    return parts.get(x) / parts.get(i);
                }
            }
        }
        return 0;
    }
}
