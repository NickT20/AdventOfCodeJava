package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day15 {
    public long ExecutePart1(String file) throws IOException {
        var result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                var parts = line.split(",");
                var partResult = 0;
                for (var part: parts) {
                    for (var x = 0; x < part.length(); x++) {
                        var character = part.charAt(x);
                        partResult += (int) character;
                        partResult *= 17;
                        partResult = partResult % 256;
                    }
                    result += partResult;
                    partResult = 0;
                }
            }
        }
        return result;
    }

    public long ExecutePart2(String file) throws IOException {
        var result = 0;
        var boxes = new HashMap<Integer, ArrayList<Label>>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                var parts = line.split(",");
                var partResult = 0;
                for (var part: parts) {
                    for (var x = 0; x < part.length(); x++) {
                        var character = part.charAt(x);
                        if (character == '-') {
                            var name = part.substring(0, x);
                            var list = boxes.get(partResult);
                            if (list == null) break;
                            for(var i = 0; i < list.size(); i++) {
                                if (Objects.equals(list.get(i).Name, name)) {
                                    list.remove(i);
                                    break;
                                }
                            }
                            break;
                        }
                        if (character == '=') {
                            var index = Integer.parseInt(part.substring(x + 1, part.length()));
                            var name = part.substring(0, x);
                            var label = new Label(name, index);
                            var box = boxes.get(partResult);
                            if (box == null) {
                                var stack = new ArrayList<Label>();
                                stack.add(label);
                                boxes.put(partResult, stack);
                            } else {
                                var b = boxes.get(partResult);
                                var s = b.stream().filter(t -> Objects.equals(t.Name, label.Name)).findFirst();
                                if (s.isPresent()) {
                                    s.get().Value = index;
                                } else {
                                    box.add(label);
                                }
                            }
                            break;
                        }
                        partResult += character;
                        partResult *= 17;
                        partResult = partResult % 256;
                    }
                    partResult = 0;
                }
            }
        }

        var keys = boxes.keySet();
        for (var key: keys) {
            var box = boxes.get(key);
            for(var x = 0; x < box.size(); x++) {
                result += (key + 1) * (x + 1) * box.get(x).Value;
            }
        }


        return result;
    }

    public class Label {
        String Name;
        int Value;

        public Label(String name, int value) {
            Name = name;
            Value = value;
        }
    }
}